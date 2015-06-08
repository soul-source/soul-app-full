'use strict';

hwc.define([
    "hwc!{PATH_JS_LIB}browser/router/include.js",
    "hwc!{PATH_JS_LIB}browser/uri/Uri.js",
    "hwc!{PATH_JS_LIB}browser/event/EventHandler.js"
], function Router () {
    var $ = this;

    return $.Browser.Router = $.class.extends($.Object)(
        /**
         * Private variables
         */
        $.private({
            routeInfo: null,
            isSpa: false,
            eventHandler: false
        }),
        /**
         * Internal Class
         */
        $.public.static({RouteInfo: $.class(
                $.private({
                    isSpa: false,
                    route: null,
                    uri: null
                }),
                $.public({
                    __construct: function (url, isSpa) {
                        this._i.uri = new $.Browser.Uri(url || window.location.href);


                        if (isSpa) {
                            this._i.isSpa = true;
                            this._i.uri.fragmentPrefix("!");
                            var fragment = this._i.uri.fragment(true);
                            this._i.route = new $.Browser.Uri(fragment.href());
                        } else {
                            this._i.route = this._i.uri.clone();
                        }
                    },
                    getComponent: function () {
                        return this._i.route.segmentCoded(0);
                    },
                    setComponent: function (component) {
                        this._i.route.segmentCoded(0, component);
                    },
                    getPath: function () {
                        return this._i.route.pathname().slice(1);
                    },
                    setPath: function (path) {
                        var component = this._i.getComponent();
                        this._i.route.pathname(component + "/" + path);
                    },
                    getParams: function () {
                        return this._i.route.search(true);
                    },
                    setParams: function (info) {
                        this._i.route.search(info);
                    },
                    addParam: function (name, value) {
                        this._i.route.addSearch(name, value);
                    },
                    removeParam: function (name, value) {
                        this._i.route.removeSearch(name, value);
                    },
                    replaceParam: function (name, value) {
                        this._i.route.removeSearch(name);
                        this._i.route.addSearch(name, value);
                    },
                    getRoute: function () {
                        return this._i.route;
                    },
                    getUri: function () {
                        return this._i.uri;
                    },
                    updateUri: function () {
                        if (this._i.isSpa) {
                            this._i.uri.fragment("!" + this._i.route.href());
                        } else {
                            this._i.uri.href(this._i.route.href());
                        }
                    },
                    clone: function () {
                        return new this.s(this._i.uri.href(), this._i.isSpa);
                    }
                })
                )
        }),
        /**
         * Public members
         */
        $.public({
            __construct: function (url, isSpa) {
                var that = this;
                this._i.isSpa = isSpa;
                this._i.eventHandler = new $.EventHandler();
                this._i.routeInfo = new this.s.RouteInfo(url, isSpa);

                /*
                 * back or forward browser events
                 */
                function pop (e) {
                    // update routeInfo and cast update trigger
                    if (that.i.getRouteInfo().getUri().href() != window.location.href)
                        that.i.update(new $.Browser.Router.RouteInfo(window.location.href, isSpa));
                }

                $.Browser.EventHandler.replaceEventListner(window, "popstate", pop);
            },
            getRouteInfo: function () {
                return  this._i.routeInfo;
            },
            getRoute: function () {
                return  this._i.routeInfo.getRoute();
            },
            addListner: function (obj) {
                this._i.eventHandler.bind(obj);
            },
            removeListner: function (obj) {
                this._i.eventHandler.unbind(obj);
            },
            setRouteByUrl: function (element, uri, callback, reload) {
                var that = this;
                var isRoute = $.typeCompare(this.s.RouteInfo, uri, !reload);

                if (typeof element === 'string') {
                    element = document.querySelector(element);
                }

                if (!element)
                    throw new Error("This element is not valid!");

                if (element.tagName.toUpperCase() === "A") {
                    element.href = isRoute && uri.getUri().toString() || uri;
                }

                function navigate (evt) {
                    evt.preventDefault();
                    that.i.navigateByUrl(uri, callback, reload);
                }

                if (!reload) {
                    $.Browser.EventHandler.replaceEventListner(element, "click", navigate);
                }
            },
            /**
             * 
             * @param {type} element
             * @param {Object} routeInfo 
             * component
             * path
             * params
             * @param {type} reload
             * @returns {undefined}
             */
            setRoute: function (element, opt, callback, reload) {
                var route = this._i.buildRoute(opt);

                this.i.setRouteByUrl(element, route, callback, reload);
            },
            navigateByUrl: function (uri, callback, reload) {
                var that = this;
                // if not reload, then typecompare will throw an error if it's not a RouteInfo obj.
                var isRoute = $.typeCompare(this.s.RouteInfo, uri, reload);

                if (reload) {
                    window.location.assign(isRoute ? uri.getUri().toString() : uri);
                    callback && callback();
                } else {
                    this.i.update(uri, callback);
                }
            },
            /**
             * 
             * @param {type} opt
             * component
             * path
             * parameters
             * @param {type} reload
             * @returns {undefined}
             */
            navigate: function (opt, callback, reload) {
                var route = this._i.buildRoute(opt);
                this.i.navigateByUrl(route, callback, reload);
            },
            jump: function (h) {
                var top = document.getElementById(h).offsetTop;
                window.scrollTo(0, top);
            },
            update: function (routeInfo, callback) {
                var that = this;

                this._i.routeInfo = routeInfo;

                callback && callback();

                this._i.eventHandler.trigger("beforeUpdate")
                    .then(this._i.eventHandler.trigger("update"))
                    .then(function (results) {
                        /*  for now params should be manually added via RouteInfo
                         var params = {};
                         for (var key in results) {
                         params = $.ObjUtils.merge(params, results[key]);
                         }
                         
                         that._i.routeInfo.setParams(params);
                         */

                        that._i.routeInfo.updateUri();

                        var url = that._i.routeInfo.getUri().href();
                        window.history.pushState(that._i.routeInfo, "Title", url);
                    })
                    .then(this._i.eventHandler.trigger("afterUpdate"));
            }
        }),
        $.private({
            buildRoute: function (opt) {
                opt = opt || {};
                var route = this._i.routeInfo.clone();
                opt.component && route.setComponent(opt.component);
                opt.path && route.setComponent(opt.path);
                opt.params && route.setComponent(opt.params);
                route.updateUri();
                return route;
            }
        })
        );
});
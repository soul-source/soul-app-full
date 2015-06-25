hwc.include([
    "hwc!{PATH_JS_LIB}browser/application/Component.js",
    "hwc!{PATH_JS_LIB}browser/cookie/index.js"
]).define(function () {
    var $ = this;

    var Menu = $.class.extends($.Browser.Component)(
            $.public({
                __construct: function (parent, childs, opt) {
                    var template = new $.Browser.Template("soul/menu.html", "css/menu.css");
                    opt.template = template;

                    this.__super(parent, childs, opt);
                },
                update: function () {
                    this.__super();
                },
                init: function () {
                    var that = this;
                    this.__super().then(function () {
                    });
                    // operazioni di inizializz.
                },
                build: function () {
                    this.__super();
                    
                    var that=this;

                    if (this.i.state === this.s.stateType.INIT) {
                        // restore global dollar for jquery
                        $.global.$ = $.Browser.JQ;

                        $.Browser.Loader.load([
                            "css/sidr/jquery.sidr.light.css",
                            "js/sidr/jquery.sidr.min.js"
                        ]).then(function () {
                            // LOAD SIDR
                            $.Browser.EventHandler.onBodyLoad(function () {
                                // use the jquery loaded for sidr
                                jQuery('#simple-menu').sidr();
                            });

                            var handler = function () {
                                jQuery.sidr("close");
                            };

                            $.Browser.EventHandler.setEventListner("body", "click", handler);

                            //$.Browser.Router.I().setRouteByUrl("#user-exit", "http://www.google.it", function () {
                            $.Browser.Router.I().setRoute("#user-exit", {component: "home"}, function () {
                                $.Browser.Cookie.delete("session-token", "/");
                                $.Browser.Cookie.delete("user-id", "/");
                            });
                        });
                    }

                    if ($.Browser.Cookie.get("session-token")) {
                        $.Browser.JQ("#user-menu-btn").html("Profilo");
                        $.Browser.Router.I().setRoute("#user-menu-btn", {component: "profile"});
                    } else {
                        $.Browser.JQ("#user-menu-btn").html("Login & Registrazione");
                        that.i.getRouter().setRoute("#user-menu-btn", {component: "login"});
                    }
                }
            }));

    return Menu;
});







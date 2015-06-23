var SOUL = {};

hwc.include([
    "hwc!{PATH_JS_LIB}browser/application/Component.js",
    "hwc!{PATH_JS_LIB}browser/application/System.js",
    "hwc!{PATH_JS_LIB}browser/cookie/index.js"
]).define(function () {
    var $ = this;

    SOUL.Main = $.class.extends($.Browser.Component)(
            $.public({
                __construct: function (parent, childs, opt) {
                    var template = new $.Browser.Template("soul/main.html", "css/main.css");
                    opt.template = template;
                    this.__super(parent, [{module: "js/nav.js", opt: {selector: "header"}}], opt);
                },
                update: function () {
                    this.__super();
                },
                init: function () {
                    this.__super().then(function () {
                        // Load libs and scripts that should be executed
                        // after all content load
                        $.Browser.Loader.load([
                            //"js/lib/jquery-1.7.min.js",
                            "js/script.js"
                        ]);
                    });
                },
                build: function () {
                    var comp = this.i.getRouter().getRouteInfo().getComponent();
                    var page = comp || "home";

                    if (this._i.prevContent) {
                        this.i.unbindChild("content");
                    }

                    $.Browser.Component.load("js/pages/" + page + ".js", "content", this.i, [], {selector: "#dyn-content"});
                },
                bindChild: function (id, child) {
                    id == "content" && (this._i.prevContent = true);

                    this.__super(id, child);
                }
            }),
            $.private({
                prevContent: null
            }),
            $.public.static({
                checkSession: function () {
                    var that = this;
                    var defer = $.Async.defer();

                    var data = {
                        id: $.Browser.Cookie.get("user-id"),
                        token: $.Browser.Cookie.get("session")
                    };

                    if (!data.id || !data.token) {
                        this.s.kick(defer.resolve);
                    } else {
                        $.Browser.JQ.post("RestApi?table=user&type=session", data).done(function (res) {
                            if (res === "false") {
                                that.s.kick(defer.resolve);
                            }
                        });
                    }

                    return defer.promise;
                },
                kick: function (callback) {
                    $.Browser.Router.I().navigate({component: "home"}, function () {
                        $.Browser.Cookie.delete("session", "/");
                        $.Browser.Cookie.delete("user-id", "/");
                        callback && callback();
                    });
                }
            })
            );

    var system = new $.Browser.System(true);

    system.register("main", SOUL.Main, {autoStart: true, selector: "body"});

    system.init();
});





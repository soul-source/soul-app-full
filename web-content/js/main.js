hwc.include([
    "hwc!{PATH_JS_LIB}browser/application/Component.js",
    "hwc!{PATH_JS_LIB}browser/application/System.js"
]).define(function () {
    var $ = this;

    var Main = $.class.extends($.Browser.Component)(
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
                this.__super();
                // operazioni di inizializz.
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
        })
        );

    var system = new $.Browser.System(true);

    system.register("main", Main, {autoStart: true, selector: "body"});

    system.init();

    $.Browser.JQ.ajax({
        url: "RestApi?user=1",
        type: "GET",
        context: document.getElementById("test")
        }).done(function (res) {

        });
});





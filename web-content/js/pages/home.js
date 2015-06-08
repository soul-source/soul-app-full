hwc.define([
    "hwc!{PATH_JS_LIB}browser/application/Component.js"
], function () {
    var $ = this;

    var Home = $.class.extends($.Browser.Component)(
        $.public({
            __construct: function (parent, childs, opt) {
                var template = new $.Browser.Template("soul/home.html", "css/home.css");
                opt.template = template;
                this.__super(parent, [], opt);
            },
            update: function () {
                this.__super();
            },
            init: function () {
                var that=this;
                this.__super().then(function() {
                    that.i.getRouter().setRoute("#news-link", {component: "news"});
                    that.i.getRouter().setRoute("#report-list-link", {component: "report-list"});
                    that.i.getRouter().setRoute("#report-link", {component: "report"});
                });
            },
            build: function () {
                this.__super();
            }
        }));

    return Home;
});





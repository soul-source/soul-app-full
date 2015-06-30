hwc.define([
    "hwc!{PATH_JS_LIB}browser/application/Component.js"
], function () {
    var $ = this;

    var Footer = $.class.extends($.Browser.Component)(
        $.public({
            __construct: function (parent, childs, opt) {
                var template = new $.Browser.Template("soul/footer.html");
                opt.template = template;

                this.__super(parent, [], opt);
            },
            update: function () {
                this.__super();
            },
            init: function () {
                var that = this;
                this.__super().then(function () {
                    that.i.getRouter().setRoute("#report-short-link", {component: "report"});
                });
            },
            build: function () {
                this.__super();
                var page = this.i.getRouter().getRouteInfo().getComponent();
                var footer = $.Browser.JQ(".hwc-footer-inner");
                page === "home" ? footer.hide() : footer.show();
            }
        }));

    return Footer;
});





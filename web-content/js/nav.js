hwc.define([
    "hwc!{PATH_JS_LIB}browser/application/Component.js"
], function () {
    var $ = this;

    var Nav = $.class.extends($.Browser.Component)(
            $.public({
                __construct: function (parent, childs, opt) {
                    var template = new $.Browser.Template("soul/nav.html", "css/nav.css");
                    opt.template = template;

                    this.__super(parent, [{module: "js/menu.js", opt: {selector: "#sidr"}}], opt);
                },
                update: function () {
                    this.__super();

                    //this.i.getRouter().getRouteInfo().replaceParam("page", Date.now());
                    //return {page: "cina"};
                },
                init: function () {
                    var that = this;
                    this.__super().then(function () {
                        that.i.getRouter().setRoute("#logo-link", {component: "home"});
                    });
                    // operazioni di inizializz.
                },
                build: function () {
                    this.__super();
                    var search=$.Browser.JQ(".hwc-home-search-btn");
                    var page = this.i.getRouter().getRouteInfo().getComponent();
                    page !== "home" ? search.hide() : search.show();
                }
            }));

    return Nav;
});





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
                    });
                },
                build: function () {
                    this.__super();
                }
            }));

    return Footer;
});




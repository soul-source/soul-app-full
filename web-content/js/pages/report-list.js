hwc.define([
    "hwc!{PATH_JS_LIB}browser/application/Component.js"
], function () {
    var $ = this;

    var Reports = $.class.extends($.Browser.Component)(
        $.public({
            __construct: function (parent, childs, opt) {
                var template = new $.Browser.Template("soul/report-list.html"/*, "css/report-list.css"*/);
                opt.template = template;
                this.__super(parent, [], opt);
            },
            update: function () {
                this.__super();
            },
            init: function () {
                this.__super();
                // operazioni di inizializz.
            },
            build: function () {
            }
        }));

    return Reports;
});





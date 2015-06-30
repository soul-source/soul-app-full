hwc.define([
    "hwc!{PATH_JS_LIB}browser/application/Component.js",
    "hwc!{PATH_JS_LIB}browser/gui/DOMTools.js"
], function () {
    var $ = this;

    var ReportList = $.class.extends($.Browser.Component)(
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
                this.__super().then(function() {
                    var rList=$.Browser.JQ("#report-list");
                    rList.append($.Browser.DOMTools.cloneId("report"));
                    rList.append($.Browser.DOMTools.cloneId("report"));
                });
            },
            build: function () {
            }
        }));

    return ReportList;
});





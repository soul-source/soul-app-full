hwc.define([
    "hwc!{PATH_JS_LIB}browser/application/Component.js"
], function () {
    var $ = this;

    var ReportInfo = $.class.extends($.Browser.Component)(
            $.public({
                __construct: function (parent, childs, opt) {
                    var template = new $.Browser.Template("soul/report-info.html", "css/report-info.css");
                    opt.template = template;
                    this.__super(parent, [], opt);
                },
                update: function () {
                    this.__super();
                },
                init: function () {
                    var that = this;
                    this.__super().then(function () {
                        var jq = $.Browser.JQ;

                        var id = $.Browser.Router.I().getRouteInfo().getPath();
                        
                        jq.ajax({
                            url: "RestApi?table=report&id="+id,
                            type: "GET",
                        }).done(function (res) {
                            console.log(res);
                             $.Browser.JQ("#emergency-subtype-name").text(res.table[0].records.soul_dbemergency_subtypename.val);
                             $.Browser.JQ("#emergency-descr").html(res.table[0].records.soul_dbemergency_subtypedescription.val);
                             //$.Browser.JQ("#emergency-descr").val(res.table[0].records.soul_dbemergency_subtypedescription.val);
                        });

                    });

                    // operazioni di inizializz.
                },
                build: function () {
                }
            })
            );

    return ReportInfo;
});





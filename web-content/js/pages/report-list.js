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
                this.__super().then(function () {
                    var jq = $.Browser.JQ;
                    jq.ajax({
                        url: "RestApi?table=report&type=list",
                        type: "GET",
                        contentType: "application/json; charset=UTF-8",
                        dataType: "json",
                        //context: document.getElementById("test")
                    }).done(function (res) {
                        console.log(res);

                        var div = jq("#report-element-1");
                        res.table.forEach(function (v, idx) {

                            var descr = div.find(".report-descr");
                            descr.text(v.records.soul_dbemergency_subtypename.val);
                            $.Browser.Router.I().setRoute(descr[0], {component: "report-info", path: v.records.soul_dbreportid_report.val})
                            div.find(".report-zone").text(v.records.soul_dbreportplace.val);
                            div.find(".report-priority").text(v.records.soul_dbemergency_subtypepriority_level.val);

                            if (idx < res.table.length - 1) {
                                div = $.Browser.DOMTools.cloneId("report-element");
                                jq("#report-list").append(div);
                            }
                        });
                        /*$.Browser.JQ("#name").val(res.table[0].records.soul_dbusername.val);
                         $.Browser.JQ("#last-name").val(res.table[0].records.soul_dbuserlast_name.val);
                         var date = new Date(res.table[0].records.soul_dbuserbirthdate.val);
                         $.Browser.JQ("#birthdate").val(
                         date.getFullYear() + "-" + $.Date.zeroFill(date.getMonth() + 1) + "-" + $.Date.zeroFill(date.getDate())
                         );
                         $.Browser.JQ("#city").val(res.table[0].records.soul_dbusercity.val);
                         $.Browser.JQ("#street").val(res.table[0].records.soul_dbuserstreet.val);
                         $.Browser.JQ("#tax_code").val(res.table[0].records.soul_dbusertax_code.val);
                         $.Browser.JQ("#cap").val(res.table[0].records.soul_dbusercap.val);
                         $.Browser.JQ("#country").val(res.table[0].records.soul_dbusercountry.val);
                         $.Browser.JQ("#password").val(res.table[0].records.soul_dbuserpassword.val);
                         // res.table[0].records.soul_dbusername.val
                         var rList=$.Browser.JQ("#report-list");
                         
                         rList.append($.Browser.DOMTools.cloneId("report"));
                         rList.append($.Browser.DOMTools.cloneId("report"));*/
                    });
                });
            },
            build: function () {
            }
        }));

    return ReportList;
});





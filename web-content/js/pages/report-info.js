hwc.define([
    "hwc!{PATH_JS_LIB}browser/application/Component.js",
    "hwc!{PATH_JS_LIB}browser/gui/DOMTools.js"
], function () {
    var $ = this;

    var ReportInfo = $.class.extends($.Browser.Component)(
        $.public({
            __construct: function (parent, childs, opt) {
                var template = new $.Browser.Template("soul/report-info.html", "css/report-info.css");
                opt.template = template;
                this.__super(parent, [], opt);
            },
            __destruct: function () {
                // remove params
                $.Browser.Router.I().getRouteInfo().removeParam("state")
            },
            update: function () {
                this.__super();
            },
            init: function () {
                var that = this;
                this.__super().then(function () {
                    var jq = $.Browser.JQ;
                    var uId = $.Browser.Cookie.get("user-id");
                    var id = $.Browser.Router.I().getRouteInfo().getPath();

                    if ($.Browser.Router.I().getRouteInfo().getParams().state == 1) {
                        jq("#report-header-info").show();
                    }

                    /**
                     *  GET REPORT INFO
                     */
                    jq.ajax({
                        url: "RestApi?table=report&type=single&id=" + id,
                        type: "GET",
                    }).done(function (res) {
                        console.log(res);
                        $.Browser.JQ("#emergency-subtype-name").text(res.table[0].records.soul_dbemergency_subtypename.val);
                        $.Browser.JQ("#emergency-descr").html(res.table[0].records.soul_dbemergency_subtypedescription.val);
                        var num = res.table[0].records.number.val;
                        var numbers = num.split(",");

                        var y = $.Browser.JQ("#emergency-num-1");
                        numbers.forEach(function (v, idx) {
                            var x = v.split("-")
                            var a = y.find("a");
                            a.attr("href", "tel: " + x[0]);
                            a.text(x);
                            if (idx < numbers.length - 1) {
                                y = $.Browser.DOMTools.cloneId("emergency-num");
                                $.Browser.JQ("#emergency-numbers").append(y);
                            }
                        });

                        $.Browser.JQ("#report-descr").text(res.table[0].records.soul_dbreportdescription.val);
                        $.Browser.JQ("#zone").text(res.table[0].records.soul_dbreportplace.val);
                        $.Browser.JQ("#priority").text(res.table[0].records.soul_dbemergency_subtypepriority_level.val);
                    });

                    /**
                     * GET USER INFO
                     */
                    jq.ajax({
                        url: "RestApi?table=report&type=user-info&id=" + id,
                        type: "GET",
                    }).done(function (res) {
                        if (res === "false")
                            $.Browser.JQ("#user-info").text("Nome:Ospite")
                        else
                            $.Browser.JQ("#user-info").text(res.table[0].records.soul_dbusername.val + " " + res.table[0].records.soul_dbuserlast_name.val);

                    });

                    /*
                     * GET COMMENTS LIST
                     */
                    jq.ajax({
                        url: "RestApi?table=comments&type=list&id=" + id,
                        type: "GET",
                    }).done(function (res) {
                        if (res === "false")
                            return null;

                        var y = $.Browser.JQ("#comment-1");
                        res.table.forEach(function (v, idx) {
                            var b = y.find("b");

                            b.text(v.records.soul_dbusername.val + " " + v.records.soul_dbuserlast_name.val);
                            var span = y.find("span");
                            span.text(v.records.soul_dbcommentmessage.val);

                            if (idx < res.table.length - 1) {
                                y = $.Browser.DOMTools.cloneId("comment");
                                $.Browser.JQ("#comment-list").append(y);
                            }
                        });
                    });

                    if (uId) {
                        jq("#create-comment").css("display", "block");
                    }

                    jq("#create-comment").submit(function (event) {
                        /* stop form from submitting normally */
                        event.preventDefault();

                        /* get some values from elements on the page: */
                        var form = jq(this);
                        var url = form.attr('action');

                        var data = {
                            comment: jq("#new-comment").val(),
                            idReport: id
                        };


                        jq.ajax({
                            url: url,
                            type: "POST",
                            data: data
                        }).done(function (res) {
                            if (res !== "false") {
                                $.Browser.Router.I().refresh();
                            }
                        });
                    })

                });




            },
            build: function () {
            }
        })
        );

    return ReportInfo;
});





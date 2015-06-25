hwc.define([
    "hwc!{PATH_JS_LIB}browser/application/Component.js"
], function () {
    var $ = this;

    var Report = $.class.extends($.Browser.Component)(
            $.public({
                __construct: function (parent, childs, opt) {
                    var template = new $.Browser.Template("soul/report.html", "css/report.css");
                    opt.template = template;
                    this.__super(parent, [], opt);
                },
                update: function () {
                    this.__super();
                },
                init: function () {
                    this.__super().then(function () {

                        var jq = $.Browser.JQ();
                        $.Browser.EventHandler.setEventListner("#send-report", "click", function () {

                            /* get some values from elements on the page: */
                            var form = jq(this),
                                    url = form.attr('action');

                            var data = {
                                address: jq("#address").val(),
                                reportType: jq("#report-type").val(),
                                reportDescription: jq("#report-description").val(),
                                picture: jq("#take-picture").val(),
                            };


                            jq.ajax({
                                url: url,
                                type: "POST",
                                data : data
                            }).done(function () {
                                
                            });


                        })

                    });

                    // operazioni di inizializz.
                },
                build: function () {
                }
            }));

    return Report;
});





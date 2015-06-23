hwc.define([
    "hwc!{PATH_JS_LIB}browser/application/Component.js",
    "hwc!{PATH_JS_LIB}date-time/index.js"
], function () {
    var $ = this;

    var Profile = $.class.extends($.Browser.Component)(
            $.public({
                __construct: function (parent, childs, opt) {
                    SOUL.Main.checkSession();

                    var template = new $.Browser.Template("soul/menu/profile.html", "css/profile.css");
                    opt.template = template;

                    this.__super(parent, [], opt);
                },
                update: function () {
                    this.__super();

                    //this.i.getRouter().getRouteInfo().replaceParam("page", Date.now());
                    //return {page: "cina"};
                },
                init: function () {
                    var that = this;
                    this.__super().then(function () {
                        // CARICARE I DATI
                        var id = $.Browser.Cookie.get("user-id");
                        $.Browser.JQ.ajax({
                            url: "RestApi?table=user&user-id=" + id,
                            type: "GET",
                            contentType: "application/json; charset=UTF-8",
                            dataType: "json",
                            //context: document.getElementById("test")
                        }).done(function (res) {
                            $.Browser.JQ("#name").val(res.table[0].records.soul_dbusername.val);
                            $.Browser.JQ("#last-name").val(res.table[0].records.soul_dbuserlast_name.val);
                            var date = new Date(res.table[0].records.soul_dbuserbirthdate.val);
                            $.Browser.JQ("#birthday").val(
                                    date.getFullYear() + "-" + $.Date.zeroFill(date.getMonth() + 1) + "-" + $.Date.zeroFill(date.getDate())
                                    );
                            $.Browser.JQ("#city").val(res.table[0].records.soul_dbusercity.val);
                            $.Browser.JQ("#street").val(res.table[0].records.soul_dbuserstreet.val);
                            $.Browser.JQ("#tax_code").val(res.table[0].records.soul_dbusertax_code.val);
                            $.Browser.JQ("#cap").val(res.table[0].records.soul_dbusercap.val);
                            $.Browser.JQ("#country").val(res.table[0].records.soul_dbusercountry.val);
                            $.Browser.JQ("#password").val(res.table[0].records.soul_dbuserpassword.val);
                            // res.table[0].records.soul_dbusername.val
                        });

                        var jq = $.Browser.JQ;
                        // FORM REGISTRAZIONE
                        jq("#profile-form").submit(function (event) {

                            /* stop form from submitting normally */
                            event.preventDefault();

                            /* get some values from elements on the page: */
                            var form = jq(this),
                                    url = form.attr('action');


                            var data = {
                                name: jq("#name").val(),
                                lastName: jq("#last-name").val(),
                                birthDay: jq("#birthday").val(),
                                city: jq("#city").val(),
                                cap: jq("#cap").val(),
                                street: jq("#street").val(),
                                country: jq("#country").val(),
                                taxCode: jq("#tax_code").val(),
                                password: jq("#password").val()
                            };
                            
                            data.id=id;


                            /* Send the data using post */
                            var posting = jq.post(url, data);

                            /* Alerts the results */
                            posting.done(function (data) {
                                alert(data);
                                form.trigger("reset");
                            });
                        });
                    });
                    // operazioni di inizializz.
                },
                build: function () {
                    this.__super();
                }
            }));

    return Profile;
});





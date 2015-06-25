hwc.define([
    "hwc!{PATH_JS_LIB}browser/application/Component.js",
    "hwc!{PATH_JS_LIB}browser/cookie/index.js"
], function () {
    var $ = this;

    var Login = $.class.extends($.Browser.Component)(
        $.public({
            __construct: function (parent, childs, opt) {
                var template = new $.Browser.Template("soul/menu/login.html", "css/login.css");
                opt.template = template;

                this.__super(parent, childs, opt);
            },
            update: function () {
                this.__super();
            },
            init: function () {
                var that = this;

                this.__super().then(function () {
                    var jq = $.Browser.JQ;
                    // FORM REGISTRAZIONE
                    jq("#soul-reg-form").submit(function (event) {

                        /* stop form from submitting normally */
                        event.preventDefault();

                        /* get some values from elements on the page: */
                        var form = jq(this),
                            url = form.attr('action');


                        var data = {
                            name: jq("#soul-reg-name").val(),
                            lastName: jq("#soul-reg-last-name").val(),
                            birthDate: jq("#soul-reg-birth-date").val(),
                            email: jq("#soul-reg-email").val(),
                            pass: jq("#soul-reg-password").val()
                        };

                        if (data.pass !== jq("#soul-reg-repeat-pass").val()) {
                            alert("Le due password non coincidono!");
                            return;
                        }

                        /* Send the data using post */
                        var posting = jq.post(url, data);

                        /* Alerts the results */
                        posting.done(function (data) {
                            alert(data);
                            form.trigger("reset");
                        });
                    });

                    // FORM LOGIN
                    jq("#soul-login-form").submit(function (event) {

                        /* stop form from submitting normally */
                        event.preventDefault();

                        /* get some values from elements on the page: */
                        var form = jq(this),
                            url = form.attr('action');


                        var data = {
                            email: jq("#soul-login-email").val(),
                            pass: jq("#soul-login-password").val() // we should encrypt pass before send
                        };

                        /* Send the data using post */
                        var posting = jq.post(url, data);

                        /* Alerts the results */
                        posting.done(function (res) {
                            //se il login è andato a buon fine salva il token nel cookie
                            if (res && res !== "false") {
                                $.Browser.Cookie.set("session-token", res.token, 365, "/");
                                $.Browser.Cookie.set("user-id", res.id, 365, "/");
                                $.Browser.Router.I().navigate({component: "home"});
                            } else {
                                alert("Login errato");
                            }

                            form.trigger("reset");
                        }).fail(function (jqXHR, textStatus, errorThrown) {
                            console.error(errorThrown);
                        });
                    });
                });
            },
            build: function () {
                this.__super();
            }
        }));

    return Login;
});





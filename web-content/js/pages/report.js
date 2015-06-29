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
                    var that = this;
                    this.__super().then(function () {
                        that._i.preparePictureBtn();
                        that._i.checkLocation();
                        var jq = $.Browser.JQ;

                        //CARICA SUBTYPE

                        jq.ajax({
                            url: "RestApi?table=emergency",
                            type: "GET",
                        }).done(function (res) {
                            jq.each(res.table, function (i, obj) {
                                jq('#report-type').append(jq('<option>', {
                                    value: i+1,
                                    text: obj.records.soul_dbemergency_subtypename.val
                                }));
                            });

                        });

                        //INVIO SEGNALAZIONE 
                        jq("#report-form").submit(function (event) {
                            /* stop form from submitting normally */
                            event.preventDefault();

                            /* get some values from elements on the page: */
                            var form = jq(this);
                            var url = form.attr('action');

                            var data = {
                                address: jq("#address").val(),
                                addressLat: jq("#address-lat").val(),
                                addressLong: jq("#address-long").val(),
                                reportType: jq("#report-type").val(),
                                reportDescription: jq("#report-description").val(),
                                picture: jq("#take-picture").val(),
                            };


                            jq.ajax({
                                url: url,
                                type: "POST",
                                data: data
                            }).done(function (res) {
                                if(res !== "false") {
                                    var id=res;
                                    $.Browser.Router.I().navigate({component: "report-info",path:id});
                                }
                            });
                        })

                    });

                    // operazioni di inizializz.
                },
                build: function () {
                }
            }),
            $.private({
                checkLocation: function () {
                    var jq = $.Browser.JQ;
                    jq(document).ready(function () {
                        if (navigator.geolocation) {
                            navigator.geolocation.getCurrentPosition(showPosition);
                        }
                        else {
                            jq('#address').innerHTML = "Geolocation is not supported by this browser.";
                        }

                        function showPosition(position) {
                            location.latitude = position.coords.latitude;
                            location.longitude = position.coords.longitude;
                            var geocoder = new google.maps.Geocoder();
                            var latLng = new google.maps.LatLng(location.latitude, location.longitude);

                            if (geocoder) {
                                geocoder.geocode({'latLng': latLng}, function (results, status) {
                                    if (status == google.maps.GeocoderStatus.OK) {
                                        console.log(results[0].formatted_address);
                                        jq('#address').val(results[0].formatted_address);
                                        jq('#address-lat').val(position.coords.latitude);
                                        jq('#address-long').val(position.coords.longitude);
                                    }
                                });
                            }

                        }

                    });
                },
                preparePictureBtn: function () {
                    var takePicture = document.querySelector("#take-picture"),
                            showPicture = document.querySelector("#show-picture");

                    if (takePicture && showPicture) {
                        // Set events
                        takePicture.onchange = function (event) {
                            // Get a reference to the taken picture or chosen file
                            var files = event.target.files,
                                    file;
                            if (files && files.length > 0) {
                                file = files[0];
                                try {
                                    // Get window.URL object
                                    var URL = window.URL || window.webkitURL;

                                    // Create ObjectURL
                                    var imgURL = URL.createObjectURL(file);

                                    // Set img src to ObjectURL
                                    showPicture.src = imgURL;

                                    // Revoke ObjectURL
                                    URL.revokeObjectURL(imgURL);
                                }
                                catch (e) {
                                    try {
                                        // Fallback if createObjectURL is not supported
                                        var fileReader = new FileReader();
                                        fileReader.onload = function (event) {
                                            showPicture.src = event.target.result;
                                        };
                                        fileReader.readAsDataURL(file);
                                    }
                                    catch (e) {
                                        //
                                        var error = document.querySelector("#error");
                                        if (error) {
                                            error.innerHTML = "Neither createObjectURL or FileReader are supported";
                                        }
                                    }
                                }
                            }
                        };
                    }
                }
            }));

    return Report;
});





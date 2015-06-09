hwc.define([
    "hwc!{PATH_JS_LIB}browser/application/Component.js"
], function () {
    var $ = this;

    var News = $.class.extends($.Browser.Component)(
            $.public({
                __construct: function (parent, childs, opt) {
                    var template = new $.Browser.Template("soul/news.html", "css/news.css");
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
                    $.Browser.JQ.ajax({
                        url: "RestApi?table=news",
                        type: "GET",
                        contentType: "application/json; charset=UTF-8",
                        dataType: "json",
                        //context: document.getElementById("test")
                    }).done(function (res) {
                        //$.Browser.JQ("body").append(res);
                        $.Browser.Loader.load("soul/news-post.html").then(function (content) {
                            content = content[0];
                            var cnt = 0;
                            try {
                                res.table.forEach(function (e) {
                                    $.Browser.JQ("#news-container").append(
                                            '<div id="news-post-' + cnt + '">' + content + '</div>'
                                            );

                                    $.Browser.JQ("#news-post-" + cnt + " .hwc-postheader").replaceWith(
                                            e.records.soul_dbnewstitle.val
                                            );
                                    
                                    $.Browser.JQ("#news-post-" + cnt + " .hwc-news-content").replaceWith(
                                            decodeURI(e.records.soul_dbnewsarticle.val)
                                    );
                                    cnt++;
                                });
                            } catch (e) {
                                console.log(e.stack);
                            }
                        });

                    });
                }
            }));

    return News;
});





/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */

'use strict';

hwc.define([
    "hwc!{PATH_JS_LIB}browser/language/include.js"
], function () {
    var $ = this;
    $.Browser.Language = $.Class({members: [
            {
                attributes: "private static",
                name: "langs",
                val: {"en": "en-GB", "it": "it-IT"}
            },
            {
                attributes: "public",
                name: "__construct",
                val: function () {
                    this.lang = window.localStorage.getItem("lang") || "it";
                    var lang = $.Browser.Uri.getInstance().getParam("lang");
                    if (lang)
                        this.lang = lang;
                }
            },
            {
                attributes: "public",
                name: "changeLang",
                val: function (lang) {
                    if (!lang in this._s.langs)
                        return false;

                    var old = this.lang;
                    this.lang = lang;

                    $.Browser.Uri.updateParam("lang", lang);
                    window.localStorage.setItem("lang", lang);

                    return old;
                }
            },
            {
                attributes: "public",
                name: "getLang",
                val: function () {
                    return this.lang;
                }
            }
        ]}
    );
});
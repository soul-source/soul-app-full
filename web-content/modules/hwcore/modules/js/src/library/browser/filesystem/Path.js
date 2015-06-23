/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */

'use strict';

hwc.define([
    "hwc!{PATH_JS_LIB}browser/filesystem/include.js",
    "hwc!{PATH_JS_LIB}filesystem/Path.js"
], function () {
    var $ = this;
    $.Browser.Path = $.Class({base: $.Path, members: [
            {
                attributes: "static",
                name: "fileExists",
                val: function (url) {
                    if (url) {
                        try {
                            var req = new XMLHttpRequest();
                            req.open('GET', url, false);
                            req.send();
                            return req.status === 200;
                        } catch (error) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        ]}
    );
});
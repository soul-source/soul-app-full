/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */

'use strict';

hwc.define([
    "hwc!{PATH_JS_LIB}browser/event/include.js"
], function () {
    var $ = this;
    $.Browser.EventHandler = $.Class({members: [
            {
                a: "public static", n: "onBodyLoad", v: function (handler) {
                    if (document.readyState === "complete") {
                        handler();
                        return;
                    }

                    this.s.replaceEventListner(window, "load", handler);
                }
            },
            {
                a: "public static", n: "setEventListner", v: function (element, event, handler, useCapture) {
                    if (typeof element=="string") {
                        // get the native element
                        element = $.Browser.JQ(element)[0];
                    }
                    
                    element.removeEventListener(event, handler);
                    element.addEventListener(event, handler, false);
                }
            }
        ]}
    );
});
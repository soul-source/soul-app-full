/*
 *  * Copyright (C) 2007 - 2015 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.common;

import javax.servlet.ServletContext;

public class SysConfig {

    private static ServletContext servlet = null;

    public static boolean isServlet() {
        return servlet != null;
    }

    public static void setServletContext(ServletContext srv) {
        servlet = srv;
    }

    public static ServletContext getServletContext() {
        return servlet;
    }
}

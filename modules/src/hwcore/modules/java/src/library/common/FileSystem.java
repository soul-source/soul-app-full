/*
 *  * Copyright (C) 2007 - 2015 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.common;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystem {

    String resPath;

    public static String getWorkingPath() {
        if (SysConfig.isServlet()) {
	    	String path=SysConfig.getServletContext().getRealPath(File.separator);
	    	return path;
        } else {
            Path currentRelativePath = Paths.get("");
            return currentRelativePath.toAbsolutePath().toString()+"/";
        }
    }
}

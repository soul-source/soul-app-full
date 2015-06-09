/*
 *  * Copyright (C) 2007 - 2015 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.common;

import hwcore.modules.java.src.library.common.MyObject;
import system.user.model.UserRS;

public class SessionHandler extends MyObject {

    private UserRS user;

    /**
     * Return the singleton instance
     *
     * @return
     */
    public static SessionHandler I() {
        return I(SessionHandler.class);
    }

    public void setUser(UserRS u) {
        user = u;
    }

    public UserRS getUser() {
        return user;
    }
}

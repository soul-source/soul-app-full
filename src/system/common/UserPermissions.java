/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */

package system.common;

import hwcore.modules.java.src.library.common.Permissions;
import java.util.HashMap;
import system.main.controller.ControllerJFrameMain;

/**
 *
 * @author giuseppe
 */
public class UserPermissions extends Permissions {
    public UserPermissions(
            Value guestPerms,
            Value userPerms,
            Value editorPerms,
            Value operatorPerms,
            Value adminPerms) {
        super();

        perms = new HashMap<>();

        perms.put(SharedDefines.UserLevel.GUEST.getLvl(), guestPerms);
        perms.put(SharedDefines.UserLevel.REGISTERED.getLvl(), userPerms);
        perms.put(SharedDefines.UserLevel.EDITOR.getLvl(), editorPerms);
        perms.put(SharedDefines.UserLevel.OPERATOR.getLvl(), operatorPerms);
        perms.put(SharedDefines.UserLevel.ADMINISTRATOR.getLvl(), adminPerms);
        
        setPerms(perms);
        setLevel(SessionHandler.I().getUser().getLevel());
    }
}

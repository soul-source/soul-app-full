/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.utente.controller;

import system.common.UserPermissions;

public class UserListPermissions extends UserPermissions {

    public UserListPermissions(boolean regMode) {
        super(new Value(regMode ? new PList[]{PList.READ,PList.UPDATE, PList.ADD} : new PList[]{PList.DENIED}), // guest
                new Value(new PList[]{PList.READ, PList.UPDATE, PList.DELETE}), // registered
                new Value(new PList[]{PList.READ, PList.UPDATE, PList.DELETE}), // editor
                new Value(new PList[]{PList.READ, PList.UPDATE, PList.DELETE}), // operator
                new Value(new PList[]{PList.READ, PList.UPDATE, PList.DELETE, PList.ADD}, true) // administrator
        );
    }

}

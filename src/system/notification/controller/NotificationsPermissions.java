/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.notification.controller;

import system.common.UserPermissions;

public class NotificationsPermissions extends UserPermissions {

    public NotificationsPermissions() {
        super(new Value(new PList[]{PList.DENIED}), // guest
                new Value(new PList[]{PList.READ}), // registered
                new Value(new PList[]{PList.READ}), // editor
                new Value(new PList[]{PList.READ}), // operator
                new Value(new PList[]{PList.READ, PList.UPDATE, PList.DELETE, PList.ADD}, true) // administrator
        );
    }

}

/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.news.controller;

import system.common.UserPermissions;

public class NewsPermissions extends UserPermissions {

    public NewsPermissions(boolean create) {
        super(new Value(new PList[]{PList.DENIED}), // guest
                new Value(new PList[]{PList.READ}), // registered
                new Value(create? 
                        new PList[]{PList.READ, PList.UPDATE, PList.DELETE, PList.ADD} :
                        new PList[]{PList.READ, PList.UPDATE, PList.DELETE} ), // editor
                new Value(new PList[]{PList.READ}), // operator
                new Value(new PList[]{PList.READ, PList.UPDATE, PList.DELETE, PList.ADD}, true) // administrator
        );
    }

}

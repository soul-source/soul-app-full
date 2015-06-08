/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */

package system.common;

import hwcore.modules.java.src.library.common.Permissions.*;

public final class PermissionsTypes {
    
    public static UserPermissions def() {
        return new UserPermissions(new Value(new PList[]{PList.DENIED}), // guest
                new Value(new PList[]{PList.READ, PList.UPDATE, PList.DELETE}), // registered
                new Value(new PList[]{PList.READ, PList.UPDATE, PList.DELETE}), // editor
                new Value(new PList[]{PList.READ, PList.UPDATE, PList.DELETE}), // operator
                new Value(new PList[]{PList.READ, PList.UPDATE, PList.DELETE, PList.ADD}, true) // administrator
        );
    }
    
    public static UserPermissions readOnly() {
        return new UserPermissions(new Value(new PList[]{PList.DENIED}), // guest
                new Value(new PList[]{PList.READ}), // registered
                new Value(new PList[]{PList.READ}), // editor
                new Value(new PList[]{PList.READ}), // operator
                new Value(new PList[]{PList.READ, PList.UPDATE, PList.DELETE, PList.ADD}, true) // administrator
        );
    }
    
    
    public static UserPermissions full() {
        return new UserPermissions(new Value(new PList[]{PList.DENIED}), // guest
                new Value(new PList[]{PList.READ, PList.UPDATE, PList.DELETE, PList.ADD}), // registered
                new Value(new PList[]{PList.READ, PList.UPDATE, PList.DELETE, PList.ADD}), // editor
                new Value(new PList[]{PList.READ, PList.UPDATE, PList.DELETE, PList.ADD}), // operator
                new Value(new PList[]{PList.READ, PList.UPDATE, PList.DELETE, PList.ADD}, true) // administrator
        );
    }
}

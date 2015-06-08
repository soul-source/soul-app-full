/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.test.model;

import hwcore.modules.java.src.library.database.EntityModel;
import system.common.MyTableModel;
import system.common.PermissionsTypes;

public class TableModelTest extends MyTableModel {
    
    public TableModelTest(EntityModel model) {
        super(new HandlerTestQuery(model), PermissionsTypes.readOnly());
    }
}

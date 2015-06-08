/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.common;

import hwcore.modules.java.src.library.common.Permissions;
import hwcore.modules.java.src.library.database.JTableModel;

public class MyTableModel extends JTableModel {

    protected Permissions perms;

    public MyTableModel(MyQueryHandler handler, Permissions perms) {
        super(handler, handler.getModel(), perms);

        this.perms = perms;
    }

    public void saveData() {
        ((MyQueryHandler) this.getHandler())
                .updateData(
                        this.getTableData(),
                        new String[]{((MyQueryHandler) this.getHandler()).getModel().getTableName()}
                );
        this.refreshList();
    }
}

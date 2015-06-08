/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.common;

import hwcore.modules.java.src.library.common.Permissions;

public class MyController {
    protected MyTableModel tableModel = null;

    public MyController(MyQueryHandler handler, Permissions perms) {
        this(new MyTableModel(handler, perms));
    }

    public MyController(MyQueryHandler handler) {
        this(handler, PermissionsTypes.def());
    }

    public MyController(MyTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public void setTableModel(MyTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public void refreshList() {
        tableModel.refreshList();
    }

    public void refreshList(String searchText) {
        tableModel.refreshList(searchText);
    }
}

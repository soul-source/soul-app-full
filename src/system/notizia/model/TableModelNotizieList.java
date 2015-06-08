/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.notizia.model;

import hwcore.modules.java.src.library.common.DateTools;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.common.MyTableModel;
import system.common.PermissionsTypes;

public class TableModelNotizieList extends MyTableModel {

    public TableModelNotizieList() {
        super(new HandlerNotiziaQuery(), PermissionsTypes.readOnly());
    }

    public void refreshList(String start,String end) {
        try {
            tableData = ((HandlerNotiziaQuery)handler).loadData(
                    new DateTools().fromString(start).getDate(),
                    new DateTools().fromString(end).getDate());
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelNotizieList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

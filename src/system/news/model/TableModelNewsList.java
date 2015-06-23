/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.news.model;

import hwcore.modules.java.src.library.common.DateTools;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.common.MyTableModel;
import system.common.PermissionsTypes;

public class TableModelNewsList extends MyTableModel {

    public TableModelNewsList() {
        super(new HandlerNewsQuery(), PermissionsTypes.readOnly());
    }

    public void refreshList(String start,String end) {
        try {
            tableData = ((HandlerNewsQuery)handler).loadData(
                    new DateTools().fromString(start).getDate(),
                    new DateTools().fromString(end).getDate());
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelNewsList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.news.model;

import hwcore.modules.java.src.library.common.Permissions;
import hwcore.modules.java.src.library.database.RecordSet;
import system.common.MyQueryHandler;
import system.common.MyTableModel;
import system.common.SessionHandler;
import system.main.controller.ControllerJFrameMain;

public class TableModelNews extends MyTableModel {

    private final int idNotizia;

    public TableModelNews(int idNotizia, Permissions perms) {
        super(new MyQueryHandler(EntityModelNews.I()), perms);
        // DEFAULT VALUES
        EntityModelNews.I().ID_UTENTE.setDefVal(SessionHandler.I().getUser().getId());
        this.idNotizia = idNotizia;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        super.setValueAt(value, row, col); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void refreshList() {
        super.refreshList(
                MyQueryHandler.getQb()
                .qbBuildName(EntityModelNews.I().ID_NOTIZIA.getPath())
                .qbCompare(idNotizia).toString()
        );
    }
    
    @Override
    protected boolean isOther(RecordSet records) {
        Object val= records.getValue(EntityModelNews.I().ID_UTENTE.getId());
        return !val.equals(SessionHandler.I().getUser().getId());
    }
}

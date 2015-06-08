/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.utente.model;

import hwcore.modules.java.src.library.common.Permissions;
import system.common.MyQueryHandler;
import system.common.MyTableModel;
import system.common.SessionHandler;
import system.main.controller.ControllerJFrameMain;

public class TableModelUtente extends MyTableModel {
    private boolean regMode;
    
    public TableModelUtente(MyQueryHandler handler,boolean regMode, Permissions perms) {
        super(handler, perms);
        this.regMode = regMode;
    }
    
    @Override
    public void refreshList() {
        // if can read other
        if (this.perms.can(SessionHandler.I().getUser().getLevel(), true, Permissions.PList.READ)) {
            super.refreshList();
        } else {
            // otherwise show only own
            super.refreshList(MyQueryHandler.getQb().qbBuildName(EntityModelUtente.I().ID_UTENTE.getPath())
                    .qbCompare(SessionHandler.I().getUser().getId()
                    ).toString());
        }
    }
}

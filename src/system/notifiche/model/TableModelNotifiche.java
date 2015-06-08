/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */

package system.notifiche.model;

import hwcore.modules.java.src.library.common.Permissions;
import hwcore.modules.java.src.library.database.querybuilders.QueryBuilder;
import system.common.MyQueryHandler;
import system.common.MyTableModel;
import system.common.SessionHandler;
import system.main.controller.ControllerJFrameMain;

public class TableModelNotifiche extends MyTableModel {
    
    public TableModelNotifiche(MyQueryHandler handler, Permissions perms) {
        super(handler, perms);
    }
    
    public void refreshList() {
        this.refreshList(false);
    }

    public void refreshList(boolean onlyNotRead) {
        QueryBuilder qb = MyQueryHandler.getQb();
        if (onlyNotRead)
            qb.qbBuildName(EntityModelNotifiche.I().LETTA.getPath()).qbCompare(false).and();
        
        // otherwise show only own
        super.refreshList(qb.qbBuildName(EntityModelNotifiche.I().ID_UTENTE.getPath())
                       .qbCompare(SessionHandler.I().getUser().getId())
                       .limit(50, 0) // show only first 50 
                       .toString()
        );
    }
}
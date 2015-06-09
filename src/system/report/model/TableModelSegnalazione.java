/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.report.model;

import hwcore.modules.java.src.library.database.RecordSet;
import system.common.MyQueryHandler;
import system.common.MyTableModel;
import system.common.PermissionsTypes;
import system.main.controller.ControllerJFrameMain;

public class TableModelSegnalazione extends MyTableModel {

    private final int idSegnalazione;

    public TableModelSegnalazione(int idSegnalazione) {
        super(new MyQueryHandler(EntityModelSegnalazione.I()), PermissionsTypes.full());

        this.idSegnalazione = idSegnalazione;
    }

    @Override
    public void refreshList() {
        super.refreshList(
                MyQueryHandler.getQb()
                .qbBuildName(EntityModelSegnalazione.I().ID_SEGNALAZIONE.getPath())
                .qbCompare(idSegnalazione).toString()
        );
    }
    
    @Override
    protected boolean isOther(RecordSet records) {
        return true;
    }
}

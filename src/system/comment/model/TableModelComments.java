/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */

package system.comment.model;

import hwcore.modules.java.src.library.database.RecordSet;
import system.comment.controller.ControllerJFrameComments;
import system.common.MyQueryHandler;
import system.common.MyTableModel;
import system.common.PermissionsTypes;
import system.common.SessionHandler;
import system.main.controller.ControllerJFrameMain;

public class TableModelComments extends MyTableModel {
    private final int segnalazioneId;

    public TableModelComments(int segnalazioneId,ControllerJFrameComments ctrl) {
        super(new HandlerCommentsQuery(segnalazioneId,ctrl), PermissionsTypes.full());
        // DEFAULT VALUES
        EntityModelComments.I().ID_UTENTE.setDefVal(SessionHandler.I().getUser().getId());
        EntityModelComments.I().ID_SEGNALAZIONE.setDefVal(segnalazioneId);
        this.segnalazioneId=segnalazioneId;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        super.setValueAt(value, row, col); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void refreshList() {
        super.refreshList(
                MyQueryHandler.getQb()
                .qbBuildName(EntityModelComments.I().ID_SEGNALAZIONE.getPath())
                .qbCompare(segnalazioneId).toString()
        );
    }

    @Override
    protected boolean isOther(RecordSet records) {
        return !records.getValue(EntityModelComments.I().ID_UTENTE.getId())
                .equals(SessionHandler.I().getUser().getId());
    }
}

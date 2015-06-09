/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.chat.model;

import system.common.MyQueryHandler;
import system.common.MyTableModel;
import system.common.PermissionsTypes;
import system.common.SessionHandler;

import system.main.controller.ControllerJFrameMain;
import system.user.model.EntityModelUtente;

public class TableModelChatList extends MyTableModel {

    public TableModelChatList() {
        super(new MyQueryHandler(EntityModelChat.I()), PermissionsTypes.readOnly());
    }

    @Override
    public void refreshList() {
        super.refreshList(MyQueryHandler.getQb()
                .qbBuildName(EntityModelMessaggio.I().ID_UTENTE.getPath())
                .qbCompare(SessionHandler.I().getUser().getValue(EntityModelUtente.I().ID_UTENTE.getId()))
                .limit(1, 0)
                .toString()
        );
    }
}

/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.chat.model;

import hwcore.modules.java.src.library.common.Permissions;
import system.common.MyQueryHandler;
import system.common.MyTableModel;
import system.common.SessionHandler;
import system.common.UserPermissions;
import system.main.controller.ControllerJFrameMain;

public class TableModelChat extends MyTableModel {

    private int chatId;

    private static class ChatPermissions extends UserPermissions {

        public ChatPermissions() {
            super(new Permissions.Value(new Permissions.PList[]{Permissions.PList.DENIED}), // guest
                    new Permissions.Value(new Permissions.PList[]{Permissions.PList.READ, Permissions.PList.ADD}), // registered
                    new Permissions.Value(new Permissions.PList[]{Permissions.PList.READ, Permissions.PList.ADD}), // editor
                    new Permissions.Value(new Permissions.PList[]{Permissions.PList.READ, Permissions.PList.UPDATE, Permissions.PList.DELETE}), // operator
                    new Permissions.Value(new Permissions.PList[]{Permissions.PList.READ, Permissions.PList.UPDATE, Permissions.PList.DELETE, Permissions.PList.ADD}, true) // administrator
            );
        }

    }

    public TableModelChat(int chatId) {
        super(new MyQueryHandler(EntityModelMessaggio.I()), new ChatPermissions());
        // DEFAULT VALUES
        EntityModelMessaggio.I().ID_UTENTE.setDefVal(SessionHandler.I().getUser().getId());
        EntityModelMessaggio.I().NOME_UTENTE.setDefVal(SessionHandler.I().getUser().getFullName());
        EntityModelMessaggio.I().ID_CHAT.setDefVal(chatId);
        this.chatId = chatId;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        super.setValueAt(value, row, col); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void refreshList() {
        super.refreshList(
                MyQueryHandler.getQb()
                .qbBuildName(EntityModelMessaggio.I().ID_CHAT.getPath())
                .qbCompare(chatId).toString()
        );
    }
}

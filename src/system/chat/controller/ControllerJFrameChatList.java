/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */

package system.chat.controller;

import system.chat.model.EntityModelChat;
import system.chat.model.TableModelChatList;
import system.chat.view.JFrameChatList;
import system.common.MyJFrameController;
import system.main.controller.ControllerJFrameMain;

public class ControllerJFrameChatList extends MyJFrameController implements MyJFrameController.HasChild {

    private ControllerJFrameChat childCtrl;

    public ControllerJFrameChatList() {
        super(new TableModelChatList());
        this.initialize(new JFrameChatList(
                this,
                ControllerJFrameMain.I().getMainFrame().jInternalFrameChat)
        );
    }

    public void openChild(int selection) {
        if (selection < 0) {
            return;
        }

        Integer idChat = (Integer) this.getTableModel()
                .getTableData()
                .getRecords()
                .get(selection)
                .getValue(EntityModelChat.I().ID_CHAT.getId());

        if (childCtrl != null) {
            closeChild();
        }

        childCtrl = new ControllerJFrameChat(idChat, this);

    }
    
    public void closeChild() {
        this.childCtrl=null;
    }
}

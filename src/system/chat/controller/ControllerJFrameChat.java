/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.chat.controller;

import system.chat.model.TableModelChat;
import system.chat.view.JFrameChat;
import system.common.MyJFrameControllerChild;

/**
 *
 *
 */
public class ControllerJFrameChat extends MyJFrameControllerChild {

    public ControllerJFrameChat(int idChat, ControllerJFrameChatList owner) {
        super(owner,new TableModelChat(idChat));
        this.initialize(new JFrameChat(this));
    }
}

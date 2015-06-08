/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.notizia.controller;

import system.common.MyJFrameControllerChild;
import system.notizia.model.TableModelNotizia;
import system.notizia.view.JFrameNotizia;

/**
 *
 *
 */
public class ControllerJFrameNotizia extends MyJFrameControllerChild {

    public ControllerJFrameNotizia(int idChat, ControllerJFrameNotizieList owner, boolean createNew) {
        super(owner, new TableModelNotizia(idChat,owner.getPermissions(createNew)));
        this.initialize(new JFrameNotizia(this, createNew));
    }
}

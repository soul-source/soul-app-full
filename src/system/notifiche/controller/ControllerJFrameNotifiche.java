/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.notifiche.controller;

import system.common.MyJFrameController;
import system.common.MyQueryHandler;
import system.main.controller.ControllerJFrameMain;
import system.notifiche.model.EntityModelNotifiche;
import system.notifiche.model.TableModelNotifiche;
import system.notifiche.view.JFrameNotifiche;

/**
 *
 *
 */
public class ControllerJFrameNotifiche extends MyJFrameController {

    public ControllerJFrameNotifiche() {
        super(
                new TableModelNotifiche(
                        new MyQueryHandler(EntityModelNotifiche.I()),
                        new NotifichePermissions()
                )
        );
        this.initialize(new JFrameNotifiche(this, ControllerJFrameMain.I().getMainFrame().jInternalFrameNotifiche));
    }

    public void refreshList(boolean onlyNotRead) {
        ((TableModelNotifiche) this.tableModel).refreshList(onlyNotRead);
    }
}

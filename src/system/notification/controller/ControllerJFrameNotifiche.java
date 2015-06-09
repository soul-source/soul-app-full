/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.notification.controller;

import system.common.MyJFrameController;
import system.common.MyQueryHandler;
import system.main.controller.ControllerJFrameMain;
import system.notification.model.EntityModelNotifiche;
import system.notification.model.TableModelNotifiche;
import system.notification.view.JFrameNotifiche;

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

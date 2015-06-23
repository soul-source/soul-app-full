/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.notification.controller;

import system.common.MyJFrameController;
import system.common.MyQueryHandler;
import system.main.controller.ControllerJFrameMain;
import system.notification.model.EntityModelNotifications;
import system.notification.model.TableModelNotifications;
import system.notification.view.JFrameNotifications;

/**
 *
 *
 */
public class ControllerJFrameNotifications extends MyJFrameController {

    public ControllerJFrameNotifications() {
        super(new TableModelNotifications(
                        new MyQueryHandler(EntityModelNotifications.I()),
                        new NotificationsPermissions()
                )
        );
        this.initialize(new JFrameNotifications(this, ControllerJFrameMain.I().getMainFrame().jInternalFrameNotifiche));
    }

    public void refreshList(boolean onlyNotRead) {
        ((TableModelNotifications) this.tableModel).refreshList(onlyNotRead);
    }
}

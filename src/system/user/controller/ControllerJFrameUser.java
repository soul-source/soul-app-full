/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.user.controller;

import javax.swing.JInternalFrame;
import system.common.MyJFrameController;
import system.main.controller.ControllerJFrameMain;
import system.user.view.JFrameUser;

public class ControllerJFrameUser extends MyJFrameController {
    
    public ControllerJFrameUser(boolean regMode) {
        super(ControllerUser.createTable(regMode));
        this.initialize(new JFrameUser(this, regMode ? new JInternalFrame() : ControllerJFrameMain.I().getMainFrame().jInternalFrameUtente, regMode));
    }
    
    public ControllerJFrameUser() {
        this(false);
    }
}

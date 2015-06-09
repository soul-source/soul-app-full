/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.user.controller;

import javax.swing.JInternalFrame;
import system.common.MyJFrameController;
import system.main.controller.ControllerJFrameMain;
import system.user.view.JFrameUtente;

public class ControllerJFrameUtente extends MyJFrameController {
    
    public ControllerJFrameUtente(boolean regMode) {
        super(ControllerUtente.createTable(regMode));
        this.initialize(new JFrameUtente(this, regMode ? new JInternalFrame() : ControllerJFrameMain.I().getMainFrame().jInternalFrameUtente, regMode));
    }
    
    public ControllerJFrameUtente() {
        this(false);
    }
}

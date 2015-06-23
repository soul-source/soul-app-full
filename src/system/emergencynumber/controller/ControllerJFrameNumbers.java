/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.emergencynumber.controller;

import system.common.MyJFrameController;
import system.common.MyQueryHandler;
import system.emergencynumber.view.JFrameNumbers;
import system.main.controller.ControllerJFrameMain;
import system.emergencynumber.model.EntityModelNumbers;

/**
 *
 *
 */
public class ControllerJFrameNumbers extends MyJFrameController {

    public ControllerJFrameNumbers() {
        super(new MyQueryHandler(EntityModelNumbers.I()));
        this.initialize(new JFrameNumbers(this, ControllerJFrameMain.I().getMainFrame().jInternalFrameNumeri));
    }
}

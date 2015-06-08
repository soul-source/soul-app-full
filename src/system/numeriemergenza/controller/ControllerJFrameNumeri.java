/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.numeriemergenza.controller;

import system.common.MyJFrameController;
import system.common.MyQueryHandler;
import system.numeriemergenza.view.JFrameNumeri;
import system.main.controller.ControllerJFrameMain;
import system.numeriemergenza.model.EntityModelNumeri;

/**
 *
 *
 */
public class ControllerJFrameNumeri extends MyJFrameController {

    public ControllerJFrameNumeri() {
        super(new MyQueryHandler(EntityModelNumeri.I()));
        this.initialize(new JFrameNumeri(this, ControllerJFrameMain.I().getMainFrame().jInternalFrameNumeri));
    }
}

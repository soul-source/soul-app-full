/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.commenti.controller;

import system.comments.model.TableModelCommenti;
import system.commenti.view.JFrameComments;
import system.common.MyJFrameControllerChild;
import system.segnalazioni.controller.ControllerJFrameSegnalazione;

public class ControllerJFrameCommenti extends MyJFrameControllerChild {

    public ControllerJFrameCommenti(int idSegnalazione, ControllerJFrameSegnalazione owner) {
        super(owner);
        this.setTableModel(new TableModelCommenti(idSegnalazione,this));
        this.initialize(new JFrameComments(this));
    }

    @Override
    public ControllerJFrameSegnalazione getOwner() {
        return (ControllerJFrameSegnalazione)super.getOwner();
    }
}

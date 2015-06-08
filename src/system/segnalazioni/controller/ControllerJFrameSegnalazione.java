/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.segnalazioni.controller;

import system.comments.controller.ControllerJFrameComments;
import system.common.MyJFrameController.HasChild;
import system.common.MyJFrameControllerChild;
import system.segnalazioni.model.EntityModelSegnalazione;
import system.segnalazioni.model.TableModelSegnalazione;
import system.segnalazioni.view.JFrameSegnalazione;

public class ControllerJFrameSegnalazione extends MyJFrameControllerChild implements HasChild {

    private ControllerJFrameComments childCtrl;

    public ControllerJFrameSegnalazione(int idSegnalazione, ControllerJFrameSegnalazioniList owner, boolean sendNew) {
        super(owner, new TableModelSegnalazione(idSegnalazione));
        this.initialize(new JFrameSegnalazione(this, sendNew));
    }

    @Override
    public void openChild(int selection) {
        if (selection < 0) {
            return;
        }

        Integer idSegnalazione = (Integer) this.getTableModel()
                .getTableData()
                .getRecords()
                .get(selection)
                .getValue(EntityModelSegnalazione.I().ID_SEGNALAZIONE.getId());

        if (childCtrl != null) {
            closeChild();
        }

        childCtrl = new ControllerJFrameComments(idSegnalazione, this);
    }

    @Override
    public void closeChild() {
        this.childCtrl = null;
    }
}

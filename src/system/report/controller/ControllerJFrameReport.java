/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.report.controller;

import system.comment.controller.ControllerJFrameComments;
import system.common.MyJFrameController.HasChild;
import system.common.MyJFrameControllerChild;
import system.report.model.EntityModelReport;
import system.report.model.TableModelReport;
import system.report.view.JFrameReport;

public class ControllerJFrameReport extends MyJFrameControllerChild implements HasChild {

    private ControllerJFrameComments childCtrl;

    public ControllerJFrameReport(int idSegnalazione, ControllerJFrameReportsList owner, boolean sendNew) {
        super(owner, new TableModelReport(idSegnalazione));
        this.initialize(new JFrameReport(this, sendNew));
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
                .getValue(EntityModelReport.I().ID_SEGNALAZIONE.getId());

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

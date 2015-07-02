package system.report.controller;

import system.common.MyJFrameController;
import system.common.PermissionsTypes;
import system.main.controller.ControllerJFrameMain;
import system.report.model.EntityModelReport;
import system.report.model.HandlerReportsQuery;
import system.report.view.JFrameReportsList;

public class ControllerJFrameReportsList extends MyJFrameController implements MyJFrameController.HasChild {

    private ControllerJFrameReport childCtrl;

    public ControllerJFrameReportsList() {
        super(new HandlerReportsQuery(EntityModelReport.WithRel.I()),PermissionsTypes.readOnly());
        this.initialize(new JFrameReportsList(
                this,
                ControllerJFrameMain.I().getMainFrame().jInternalFrameSegnalazioni)
        );
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

        childCtrl = new ControllerJFrameReport(idSegnalazione, this, false);
    }
    
    public void openSendReport() {
        new ControllerJFrameReport(0, this, true);
    }

    @Override
    public void closeChild() {
        this.childCtrl = null;
    }
}

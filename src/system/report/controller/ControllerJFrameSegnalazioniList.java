package system.report.controller;

import system.common.MyJFrameController;
import system.common.PermissionsTypes;
import system.main.controller.ControllerJFrameMain;
import system.report.model.EntityModelSegnalazione;
import system.report.model.HandlerSegnalazioniQuery;
import system.report.view.JFrameSegnalazioniList;

public class ControllerJFrameSegnalazioniList extends MyJFrameController implements MyJFrameController.HasChild {

    private ControllerJFrameSegnalazione childCtrl;

    public ControllerJFrameSegnalazioniList() {
        super(new HandlerSegnalazioniQuery(),PermissionsTypes.readOnly());
        this.initialize(new JFrameSegnalazioniList(
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
                .getValue(EntityModelSegnalazione.I().ID_SEGNALAZIONE.getId());

        if (childCtrl != null) {
            closeChild();
        }

        childCtrl = new ControllerJFrameSegnalazione(idSegnalazione, this, false);
    }
    
    public void openSendReport() {
        new ControllerJFrameSegnalazione(0, this, true);
    }

    @Override
    public void closeChild() {
        this.childCtrl = null;
    }
}

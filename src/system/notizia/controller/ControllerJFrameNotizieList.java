package system.notizia.controller;

import system.common.MyJFrameController;
import system.main.controller.ControllerJFrameMain;
import system.notizia.model.EntityModelNotizia;
import system.notizia.model.TableModelNotizieList;
import system.notizia.view.JFrameNotiziaList;

public class ControllerJFrameNotizieList extends MyJFrameController implements MyJFrameController.HasChild {

    private ControllerJFrameNotizia childCtrl;
    private NotiziaPermissions perms[];

    public ControllerJFrameNotizieList() {
        super(new TableModelNotizieList());

        this.perms = new NotiziaPermissions[]{
            new NotiziaPermissions(false), // 0
            new NotiziaPermissions(true) // 1
        };

        this.initialize(new JFrameNotiziaList(
                this,
                ControllerJFrameMain.I().getMainFrame().jInternalFrameNotizie)
        );
    }

    public NotiziaPermissions getPermissions(boolean create) {
        return create ? this.perms[1] : this.perms[0];
    }

    public void openCreateFrame() {
        new ControllerJFrameNotizia(0, this, true);
    }

    @Override
    public void openChild(int selection) {
        if (selection < 0) {
            return;
        }

        Integer idNotizia = (Integer) this.getTableModel()
                .getTableData()
                .getRecords()
                .get(selection)
                .getValue(EntityModelNotizia.I().ID_NOTIZIA.getId());

        if (childCtrl != null) {
            closeChild();
        }

        childCtrl = new ControllerJFrameNotizia(idNotizia, this, false);
    }

    @Override
    public void closeChild() {
        this.childCtrl = null;
    }

    public void refreshList(String start, String end) {
        ((TableModelNotizieList) this.tableModel).refreshList(start, end);
    }

}

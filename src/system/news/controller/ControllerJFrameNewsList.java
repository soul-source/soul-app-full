package system.news.controller;

import system.common.MyJFrameController;
import system.main.controller.ControllerJFrameMain;
import system.news.model.EntityModelNews;
import system.news.model.TableModelNewsList;
import system.news.view.JFrameNewsList;

public class ControllerJFrameNewsList extends MyJFrameController implements MyJFrameController.HasChild {

    private ControllerJFrameNews childCtrl;
    private NewsPermissions perms[];

    public ControllerJFrameNewsList() {
        super(new TableModelNewsList());

        this.perms = new NewsPermissions[]{
            new NewsPermissions(false), // 0
            new NewsPermissions(true) // 1
        };

        this.initialize(new JFrameNewsList(
                this,
                ControllerJFrameMain.I().getMainFrame().jInternalFrameNotizie)
        );
    }

    public NewsPermissions getPermissions(boolean create) {
        return create ? this.perms[1] : this.perms[0];
    }

    public void openCreateFrame() {
        new ControllerJFrameNews(0, this, true);
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
                .getValue(EntityModelNews.I().ID_NOTIZIA.getId());

        if (childCtrl != null) {
            closeChild();
        }

        childCtrl = new ControllerJFrameNews(idNotizia, this, false);
    }

    @Override
    public void closeChild() {
        this.childCtrl = null;
    }

    public void refreshList(String start, String end) {
        ((TableModelNewsList) this.tableModel).refreshList(start, end);
    }

}

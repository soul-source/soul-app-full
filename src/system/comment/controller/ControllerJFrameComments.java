package system.comment.controller;

import system.comment.model.TableModelComments;
import system.comment.view.JFrameComments;
import system.common.MyJFrameControllerChild;
import system.report.controller.ControllerJFrameReport;

public class ControllerJFrameComments extends MyJFrameControllerChild {

    public ControllerJFrameComments(int idSegnalazione, ControllerJFrameReport owner) {
        super(owner);
        this.setTableModel(new TableModelComments(idSegnalazione,this));
        this.initialize(new JFrameComments(this));
    }

    @Override
    public ControllerJFrameReport getOwner() {
        return (ControllerJFrameReport)super.getOwner();
    }
}

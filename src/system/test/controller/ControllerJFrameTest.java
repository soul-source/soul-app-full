/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.test.controller;

import system.common.MyJFrameController;
import system.main.controller.ControllerJFrameMain;
import system.segnalazioni.model.EntityModelSegnalazione;
import system.test.model.HandlerTestQuery;
import system.test.model.TableModelTest;
import system.test.view.JFrameTest;
import system.utente.model.EntityModelUtenteProfile;

public class ControllerJFrameTest extends MyJFrameController {

    public ControllerJFrameTest() {
        super(new TableModelTest(EntityModelUtenteProfile.WithIncarico.I()));
        this.initialize(new JFrameTest(this, ControllerJFrameMain.I().getMainFrame().jInternalFrameTest));
    }
    
    public void loadUtenti() {
        this.setTableModel(new TableModelTest(EntityModelUtenteProfile.WithIncarico.I()));
        ((JFrameTest)this.frame).getjTable().setModel(this.getTableModel());
        this.getTableModel().refreshList();
    }
    
    public void loadSegnalazioni() {
        this.setTableModel(new TableModelTest(EntityModelSegnalazione.WithRel.I()));
        ((JFrameTest)this.frame).getjTable().setModel(this.getTableModel());
        this.getTableModel().refreshList();
    }
    
    
    public void insertCommento() {
        ((HandlerTestQuery)this.getTableModel().getHandler()).insertCommento();
    }
    
    public void deleteCommento() {
        ((HandlerTestQuery)this.getTableModel().getHandler()).deleteComment();
    }
}

/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.main.controller;

import system.common.SessionHandler;
import system.main.view.JDialogLogin;
import system.user.model.EntityModelUtente;
import system.user.model.HandlerUtenteQuery;
import system.user.model.UserRS;

public class ControllerJDialogLogin extends javax.swing.JDialog {

    public JDialogLogin dialog = null;

    public ControllerJDialogLogin() {
        this.dialog = new JDialogLogin(this);
        this.dialog.setVisible(true);
    }

    public boolean login(String email, String password) {
        HandlerUtenteQuery handler = new HandlerUtenteQuery(EntityModelUtente.I());
        UserRS utente = handler.loadUtente(email, password);
        if (utente != null) {
            SessionHandler.I().setUser(utente);
            this.dialog.setVisible(false);
            this.dialog.dispose();
            this.dialog = null;
            
            ControllerJFrameMain.I().refreshAll();
            return true;
        }

        return false;
    }

    public void close() {
        if (SessionHandler.I().getUser()== null) {
            System.exit(0);
        }
    }
}

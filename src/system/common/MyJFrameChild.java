/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.common;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import system.main.controller.ControllerJFrameMain;

public class MyJFrameChild extends MyJFrame {

    public MyJFrameChild(MyJFrameController controller, String title) {
        super(controller, new JInternalFrame(title));
    }

    @Override
    public void initialize(JButton button, Container contentPane, JTable table) {
        this.iFrame.setClosable(true);
        this.iFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        this.iFrame.setIconifiable(true);
        this.iFrame.setMaximizable(true);
        this.iFrame.setResizable(true);
        this.iFrame.setVisible(true);
        this.iFrame.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            @Override
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                jInternalFrameClosing(evt);
            }

            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }

            @Override
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        ControllerJFrameMain.I().getMainFrame().getjDesktopPane().add(this.iFrame);
        this.iFrame.setBounds(0, 0, 624, 371);
        try {
            this.iFrame.setIcon(false);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        super.initialize(button, contentPane, table); //To change body of generated methods, choose Tools | Templates.
        this.setFrameState(false);
        this.setFrameState(true);
    }

    private void jInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
        ControllerJFrameMain.I().getMainFrame().getjDesktopPane().remove(this.iFrame);
        controller.dispose();
    }

    @Override
    public void dispose() {
        this.iFrame.dispose();
        super.dispose();
    }
}

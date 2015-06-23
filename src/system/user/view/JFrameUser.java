/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.user.view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import system.common.MyJFrame;
import system.user.controller.ControllerJFrameUser;

public class JFrameUser extends MyJFrame {

    public JFrameUser(ControllerJFrameUser controller, JInternalFrame iFrame, boolean regMode) {
        super(controller, iFrame);
        initComponents();
        initialize(regMode);
    }

    /**
     * Initialize the class.
     */
    private void initialize(boolean regMode) {
        this.initialize(jButtonOut, rootPane, jTable);
        if (regMode) {
            this.setFrameState(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelContent = new javax.swing.JPanel();
        jPanelCenter = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanelSouth = new javax.swing.JPanel();
        jPanelBottomRight = new javax.swing.JPanel();
        jButtonDelete = new javax.swing.JButton();
        jButtonAnnulla = new javax.swing.JButton();
        jButtonSalva = new javax.swing.JButton();
        jButtonOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modifica Distributore");
        setResizable(false);

        jPanelContent.setLayout(new java.awt.BorderLayout());

        jPanelCenter.setLayout(new java.awt.BorderLayout());

        jScrollPane.setPreferredSize(new java.awt.Dimension(452, 300));

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable.setCellSelectionEnabled(true);
        jScrollPane.setViewportView(jTable);

        jPanelCenter.add(jScrollPane, java.awt.BorderLayout.NORTH);

        jPanelContent.add(jPanelCenter, java.awt.BorderLayout.CENTER);

        jPanelSouth.setLayout(new java.awt.BorderLayout());

        jButtonDelete.setText("Elimina Utente");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanelBottomRight.add(jButtonDelete);

        jButtonAnnulla.setText("Annulla");
        jButtonAnnulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnnullaActionPerformed(evt);
            }
        });
        jPanelBottomRight.add(jButtonAnnulla);

        jButtonSalva.setText("Salva");
        jButtonSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvaActionPerformed(evt);
            }
        });
        jPanelBottomRight.add(jButtonSalva);

        jPanelSouth.add(jPanelBottomRight, java.awt.BorderLayout.EAST);

        jButtonOut.setText("Stacca");
        jButtonOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOutActionPerformed(evt);
            }
        });
        jPanelSouth.add(jButtonOut, java.awt.BorderLayout.LINE_START);

        jPanelContent.add(jPanelSouth, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanelContent, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnullaActionPerformed
        this.controller.dispose();
    }//GEN-LAST:event_jButtonAnnullaActionPerformed

    private void jButtonSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvaActionPerformed
        this.controller.save();
    }//GEN-LAST:event_jButtonSalvaActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        try {
            removeSelectedRows(jTable);
        } catch (Exception ex) {
            Logger.getLogger(JFrameUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOutActionPerformed
        this.changeFrameState();
    }//GEN-LAST:event_jButtonOutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnnulla;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonOut;
    private javax.swing.JButton jButtonSalva;
    private javax.swing.JPanel jPanelBottomRight;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelSouth;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables

}

/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.news.view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import system.common.MyJFrameChild;
import system.news.controller.ControllerJFrameNews;

public class JFrameNews extends MyJFrameChild {

    private boolean create;

    public JFrameNews(ControllerJFrameNews controller, boolean create) {
        super(controller, "Notizia");
        this.create = create;
        initComponents();
        initialize();
    }

    /**
     * Initialize the class.
     */
    private void initialize() {
        this.initialize(jButtonOut, rootPane, jTable);
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
        jButtonOut = new javax.swing.JButton();
        jPanelBottomRight = new javax.swing.JPanel();
        jButtonDelete = new javax.swing.JButton();
        jButtonSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Messaggio");
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
        jScrollPane.setViewportView(jTable);

        jPanelCenter.add(jScrollPane, java.awt.BorderLayout.NORTH);

        jPanelContent.add(jPanelCenter, java.awt.BorderLayout.CENTER);

        jPanelSouth.setLayout(new java.awt.BorderLayout());

        jButtonOut.setText("Stacca");
        jButtonOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOutActionPerformed(evt);
            }
        });
        jPanelSouth.add(jButtonOut, java.awt.BorderLayout.LINE_START);

        jButtonDelete.setText("Elimina");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanelBottomRight.add(jButtonDelete);

        jButtonSend.setText("Salva");
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });
        jPanelBottomRight.add(jButtonSend);

        jPanelSouth.add(jPanelBottomRight, java.awt.BorderLayout.EAST);

        jPanelContent.add(jPanelSouth, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanelContent, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOutActionPerformed
        this.changeFrameState();
    }//GEN-LAST:event_jButtonOutActionPerformed

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        this.controller.save();
        this.controller.dispose();
    }//GEN-LAST:event_jButtonSendActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        if (create) {
            this.controller.dispose();
        } else {
            try {
                removeSelectedRows(jTable);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Cannot delete this!", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            jButtonSend.doClick(); // execute SendActionPerformed
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonOut;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JPanel jPanelBottomRight;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelSouth;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables

}

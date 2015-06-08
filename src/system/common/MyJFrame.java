/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.common;

import hwcore.modules.java.src.library.common.GuiTools;
import hwcore.modules.java.src.library.database.DbTableWorkers;
import hwcore.modules.java.src.library.database.JTableModel;
import java.awt.Container;
import java.awt.HeadlessException;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JTable;

abstract public class MyJFrame extends javax.swing.JFrame {

    protected MyJFrameController controller;
    protected JInternalFrame iFrame;
    protected boolean isInternal;
    private javax.swing.JButton detachBtn;
    private JTable jTable;

    public MyJFrame(MyJFrameController controller, JInternalFrame iFrame) throws HeadlessException {
        this.controller = controller;
        this.iFrame = iFrame;
    }

    public void initialize(javax.swing.JButton button, Container contentPane, boolean setDefGeometry, JTable table) {
        detachBtn = button;
        //setContentPane(contentPane);
        setTitle(iFrame.getTitle());
        this.jTable = table;

        this.setFrameState(true);

        if (setDefGeometry) {
            setSize(new java.awt.Dimension(640, 480));
            setPreferredSize(getSize());
            GuiTools.setWindowCenterPosition(this);
        }

        table.setModel(controller.getTableModel());
        table.getTableHeader().setReorderingAllowed(false);
        DbTableWorkers.setTableEditors(table);
        DbTableWorkers.setTableRenderers(table);
    }

    public void initialize(javax.swing.JButton button, Container contentPane, JTable table) {
        this.initialize(button, contentPane, true, table);
    }

    public JTable getjTable() {
        return jTable;
    }

    public void changeFrameState() {
        this.setFrameState(!isInternal);
    }

    // final , to avoid method override
    public final void setFrameState(boolean isInternal) {
        this.isInternal = isInternal;
        String text = !isInternal ? "attacca" : "stacca";
        detachBtn.setText(text);
        GuiTools.setWindowMode(iFrame, this, isInternal);
    }

    public void removeSelectedRows(JTable table) throws Exception {
        JTableModel model = (JTableModel) table.getModel();
        int[] rows = table.getSelectedRows();
        for (int i = 0; i < rows.length; i++) {
            model.removeRow(rows[i] - i);
        }
    }

    @Override
    public void dispose() {
        try {
            this.iFrame.setIcon(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MyJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.dispose();
    }
}

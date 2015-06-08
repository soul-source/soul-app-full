/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.common;

import hwcore.modules.java.src.library.common.Permissions;
import hwcore.modules.java.src.library.database.JTableModel;
import javax.swing.table.TableCellEditor;
import system.main.controller.ControllerJFrameMain;

public class MyJFrameController extends MyController {

    public static interface HasChild {

        public void openChild(int selection);

        public void closeChild();
    }

    // must be initialized in superclass for a limitation of Java
    protected MyJFrame frame = null;

    public MyJFrameController(MyQueryHandler handler, Permissions perms) {
        this(new MyTableModel(handler, perms));
    }

    public MyJFrameController(MyQueryHandler handler) {
        this(handler, PermissionsTypes.def());
    }

    public MyJFrameController(MyTableModel tableModel) {
        super(tableModel);
    }

    protected void initialize(MyJFrame frame) {
        this.frame = frame;
    }

    public MyJFrame getFrame() {
        return frame;
    }

    public JTableModel getTableModel() {
        return tableModel;
    }

    public void dispose() {
        frame.dispose();
        frame = null;
        ControllerJFrameMain.I().setCtrl(this.getClass().getName(), null);
        // refresh the UI at window dispose
        ControllerJFrameMain.I().getMainFrame().getjDesktopPane().updateUI();
    }

    public void save() {
        TableCellEditor editor = frame.getjTable().getCellEditor();
        if (editor != null) {
            editor.stopCellEditing();
        }

        tableModel.saveData();
    }

    public void doDefaultFocus() {
        // TODO: implement
    }
}

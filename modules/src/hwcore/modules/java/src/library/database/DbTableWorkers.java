/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.database;

import java.awt.Component;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.table.DatePickerCellEditor;

public class DbTableWorkers {

    public static class TimestampRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            TableModel tm = table.getModel();
            Timestamp date = (Timestamp) tm.getValueAt(table.convertRowIndexToModel(row), column);
            if (date != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                this.setText(sdf.format(date));
            } else {
                this.setText("");
            }

            return this;
        }

    }

    public static void setTableRenderers(JTable table) {
        table.setDefaultRenderer(Timestamp.class, new TimestampRenderer());
    }

    public static void setTableEditors(JTable table) {
        // TODO fix date formats
        table.setDefaultEditor(Date.class, new DatePickerCellEditor(DateFormat.getDateInstance(DateFormat.SHORT)));
        table.setDefaultEditor(Timestamp.class, new DatePickerCellEditor(DateFormat.getDateInstance(DateFormat.LONG)));
    }

    /*public static class DateEditor extends DefaultCellEditor {
     }*/
}

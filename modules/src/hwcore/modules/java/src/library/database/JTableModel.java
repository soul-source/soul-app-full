/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.database;

import hwcore.modules.java.src.library.common.Permissions;
import hwcore.modules.java.src.library.database.fielddecorators.RestrictedField;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class JTableModel extends AbstractTableModel {

    private class ColumnDecorator {

        private final Integer colIndex;
        private final FieldModel model;

        public ColumnDecorator(Integer colIndex, FieldModel model) {
            this.colIndex = colIndex;
            this.model = model;
        }

        public Integer getColIndex() {
            return colIndex;
        }

        public FieldModel getModel() {
            return model;
        }
    }

    protected TableData tableData = null;
    private final EntityModel model;
    // array that contains real db column value for each visual column
    private ArrayList<ColumnDecorator> visibleFields;
    private int colCnt;
    LinkedHashMap<DbId, FieldModel> fields;
    protected final QueryHandler handler;
    protected Permissions perms;
    private boolean canAdd;

    public JTableModel(QueryHandler handler, EntityModel model, Permissions perms) {
        this.handler = handler;
        this.model = model;
        this.fields = this.model.getFields();
        this.perms = perms;
        this.canAdd = perms.can(false, Permissions.PList.ADD);
        // count only visible fields
        int col = 0;
        visibleFields = new ArrayList<>();
        for (Map.Entry<DbId, FieldModel> entry : this.fields.entrySet()) {
            if (entry.getValue() instanceof VisualName) {
                visibleFields.add(new ColumnDecorator(col, entry.getValue()));
                this.colCnt++;
            }
            col++;
        }
    }

    public JTableModel(QueryHandler handler, EntityModel model) {
        this(handler, model, new Permissions());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (this.canAdd && rowIndex == this.getRowCount() - 1) {
            return null;
        }

        DbVar val = getField(rowIndex, visibleFields.get(columnIndex).getColIndex());
        return val != null ? val.getValue() : null;
    }

    @Override
    public String getColumnName(int col) {
        String name = ((VisualName) visibleFields.get(col).getModel()).getName();
        return name != null ? name : "Error";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class cl = String.class;
        try {
            String clName = tableData.getFieldClassName(visibleFields.get(columnIndex).getModel());
            // check calculated-field case
            if (clName != null && !clName.isEmpty()) {
                cl = Class.forName(clName);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cl;
    }

    @Override
    public int getColumnCount() {
        return this.colCnt;
    }

    @Override
    public int getRowCount() {
        return this.getTableData().getRecords().size() + (this.canAdd ? 1 : 0);
    }

    public TableData getTableData() {
        if (tableData == null) {
            this.refreshList();
        }

        return tableData;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Object val = getValueAt(row, col);
        if (val != null && val.toString().equals(value)) {
            return;
        }

        super.setValueAt(value, row, col);
        if (this.canAdd && row == this.getRowCount() - 1) {
            if (!value.equals("")) {
                // create new row with inserted value
                LinkedHashMap<DbId, DbVar> data = new LinkedHashMap<>();
                int i = 0;
                for (FieldModel field : this.fields.values()) {
                    data.put(
                            field.getId(),
                            new DbVar(
                                    i == visibleFields.get(col).getColIndex() ? value : field.getDefVal(),
                                    field.getFieldName(),
                                    field.getEntityModel().getTableName(),
                                    field.getEntityModel().getSchemaModel().getDbModel().getDbName(),
                                    field.getEntityModel().getSchemaModel().getSchemaName(),
                                    tableData.getPrimaryKeys().contains(field.getId())
                            )
                    );

                    i++;
                }

                this.getTableData().add(new RecordSet(data));
            }
        } else {
            this.getTableData().update(row, visibleFields.get(col).getModel().getId(), value);
        }

        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        FieldModel model = visibleFields.get(column).getModel();
        return ((this.canAdd && row == this.getRowCount() - 1) || perms.can(isOther(this.getTableData().getRecords().get(row)), Permissions.PList.UPDATE))
                && (!(model instanceof RestrictedField)
                || ((RestrictedField) model).getPermissions().can(Permissions.PList.UPDATE));
    }

    public void removeRow(int i) throws Exception {
        if (perms.can(isOther(this.getTableData().getRecords().get(i)), Permissions.PList.DELETE)) {
            this.getTableData().remove(i);
            fireTableDataChanged();
        } else {
            throw new Exception("Cannot delete this row!");
        }
    }

    public void refreshList(String searchCondition) {
        tableData = handler.loadData(searchCondition);
        fireTableDataChanged();
    }

    public void refreshList() {
        this.refreshList("");
    }

    public QueryHandler getHandler() {
        return handler;
    }

    public EntityModel getModel() {
        return model;
    }

    public DbVar getField(int row, int col) {
        List<RecordSet> records = this.getTableData().getRecords();
        DbVar[] var = {};
        if (!records.isEmpty()) {
            var = records.get(row)
                    .getRecordValues();
        }

        return var.length > 0 ? var[col] : null;
    }

    protected boolean isOther(RecordSet records) {
        return true;
    }
}

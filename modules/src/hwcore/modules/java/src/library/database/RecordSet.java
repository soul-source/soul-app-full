/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.database;

import hwcore.modules.java.src.library.common.StatusVar;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class RecordSet {

    /**
     * Key: column alias , Value: value of field
     */
    private final LinkedHashMap<DbId, DbVar> records = new LinkedHashMap<>();

    public RecordSet(ResultSet rs, TableData tableData) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        for (int i = 0; i < cols; i++) {
            Object obj = rs.getObject(i + 1);
            String fieldName = meta.getColumnName(i + 1);
            String tableName = meta.getTableName(i + 1);
            String schemaName = meta.getSchemaName(i + 1);
            String catName = meta.getCatalogName(i + 1);
            boolean isPrimary = false;
            try {
                // In case of exception isPrimary is false
                isPrimary = tableData.getPrimaryKeys().contains(
                        new DbId.Field(catName, schemaName, tableName, fieldName
                        ));
            } catch (Exception e) {
            }

            DbVar v = new DbVar(obj,
                    fieldName,
                    tableName,
                    schemaName,
                    catName,
                    isPrimary
            );

            records.put(
                    v.getId(),
                    v
            );
        }
    }

    public RecordSet(LinkedHashMap<DbId, DbVar> values) {
        for (Map.Entry<DbId, DbVar> entry : values.entrySet()) {
            records.put(
                    entry.getKey(),
                    entry.getValue()
            );
        }
    }

    public DbVar[] getRecordValues() {
        return records.values().toArray(new DbVar[records.values().size()]);
    }

    public String[] getStringValues() {
        return records.values().toArray(new String[records.values().size()]);
    }

    public String[] getFieldNames() {
        return (String[]) records.keySet().toArray();
    }

    public LinkedHashMap<DbId, DbVar> getRecords() {
        return records;
    }

    public StatusVar getAccessor(DbId key) {
        return records.get(key);
    }

    /*
     Shortcuts for accessor
     */
    public Object getValue(DbId key) {
        return this.getAccessor(key).getValue();
    }

    public <T> void setValue(DbId key, T val) {
        this.getAccessor(key).setValue(val);
    }
}

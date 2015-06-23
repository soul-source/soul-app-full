/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.database;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableData implements Cloneable {

    private List<RecordSet> records;
    private ResultSetMetaData metaData;
    private ArrayList<RecordSet> removed, inserted, updated;
    private Class<RecordSet> recordSet;
    /**
     * Table-PrimaryKeys
     */
    private HashSet<DbId.Field> primaryKeys;
    private HashMap<String, HashMap<String, HashMap<String, HashMap<String, String>>>> dbMap;

    public TableData(QueryHandler handler) throws SQLException {
        records = new ArrayList<>();
        this.recordSet = handler.getRsClass();

        ResultSet rs;
        rs = handler.getPrepStat().getResultSet();

        if (rs == null) // empty resultset?
        {
            return;
        }

        this.metaData = rs.getMetaData();
        this.primaryKeys = new HashSet<>();
        this.dbMap = new HashMap<>();

        this.retrieveSpecialData(this.metaData, handler.conn.getMetaData());

        try {
            this.metaData = rs.getMetaData();
            while (rs.next()) {
                try {
                    records.add(
                            recordSet.getDeclaredConstructor(ResultSet.class, TableData.class)
                            .newInstance(rs, this)
                    );
                } catch (Exception ex) {
                    Logger.getLogger(TableData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            removed = new ArrayList<>();
            updated = new ArrayList<>();
            inserted = new ArrayList<>();
        } catch (SQLException ex) {
            Logger.getLogger(TableData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<RecordSet> getRecords() {
        return records;
    }

    public ResultSetMetaData getMetaData() {
        return metaData;
    }

    public HashSet<DbId.Field> getPrimaryKeys() {
        return primaryKeys;
    }

    public ArrayList<RecordSet> getInserted() {
        return inserted;
    }

    public ArrayList<RecordSet> getRemoved() {
        return removed;
    }

    public ArrayList<RecordSet> getUpdated() {
        return updated;
    }

    public HashMap<String, HashMap<String, HashMap<String, HashMap<String, String>>>> getDbMap() {
        return dbMap;
    }

    public String getFieldClassName(FieldModel model) {
        return dbMap.get(model.getEntityModel().getSchemaModel().getDbModel().getDbName())
                .get(model.getEntityModel().getSchemaModel().getSchemaName())
                .get(model.getEntityModel().getTableName())
                .get(model.getFieldName());

    }

    public void remove(int index) {
        RecordSet rm = this.getRecords().remove(index);
        if (rm != null) {
            this.removed.add(rm);
            // remove also from other list if present
            this.inserted.remove(rm);
            this.updated.remove(rm);
        }
    }

    public void add(RecordSet rs) {
        this.getRecords().add(rs);
        this.inserted.add(rs);
    }

    public <V> void update(int index, DbId col, V val) {
        this.getRecords().get(index).setValue(col, val);
        RecordSet rs = this.getRecords().get(index);
        // check if update/insert row for this field already exists
        if (!this.inserted.contains(rs) && !this.updated.contains(rs)) {
            this.updated.add(this.getRecords().get(index));
        }
    }

    private void retrieveSpecialData(ResultSetMetaData rsMeta, DatabaseMetaData dbMeta) {
        HashMap<String, HashMap<String, HashMap<String, HashMap<String, String>>>> dbMap = new HashMap<>();

        try {
            for (int col = 1; col <= rsMeta.getColumnCount(); col++) {
                String catalog = rsMeta.getCatalogName(col);
                String schema = rsMeta.getSchemaName(col);
                String table = rsMeta.getTableName(col);
                String field = rsMeta.getColumnName(col);
                String clName = rsMeta.getColumnClassName(col);

                if (!dbMap.containsKey(catalog)) {
                    dbMap.put(catalog, new HashMap<String, HashMap<String, HashMap<String, String>>>());
                }

                if (!dbMap.get(catalog).containsKey(schema)) {
                    dbMap.get(catalog).put(schema, new HashMap<String, HashMap<String, String>>());
                }

                if (!dbMap.get(catalog).get(schema).containsKey(table)) {
                    dbMap.get(catalog).get(schema).put(table, new HashMap<String, String>());

                    if (table == null || table.isEmpty()) // calculated fields ( not table related )
                    {
                        continue;
                    }

                    try {
                        ResultSet rs = dbMeta.getPrimaryKeys(catalog, schema, table);
                        while (rs.next()) {

                            String pkName = rs.getString("COLUMN_NAME");
                            primaryKeys.add(new DbId.Field(catalog, schema, table, pkName));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (!dbMap.get(catalog).get(schema).get(table).containsKey(field)) {
                    dbMap.get(catalog).get(schema).get(table).put(field, clName);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dbMap = dbMap;
    }
}

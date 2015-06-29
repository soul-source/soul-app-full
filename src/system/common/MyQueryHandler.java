/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.common;

import hwcore.modules.java.src.library.common.Entry;
import hwcore.modules.java.src.library.common.If;
import hwcore.modules.java.src.library.database.DbModel;
import hwcore.modules.java.src.library.database.DbVar;
import hwcore.modules.java.src.library.database.EntityModel;
import hwcore.modules.java.src.library.database.DbId;
import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.QueryHandler;
import hwcore.modules.java.src.library.database.RecordSet;
import hwcore.modules.java.src.library.database.TableData;
import hwcore.modules.java.src.library.database.querybuilders.QueryBuilder;
import hwcore.modules.java.src.library.database.querybuilders.SqlQueryBuilder;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giuseppe
 */
public class MyQueryHandler extends QueryHandler {

    private static DbModel dbModel;
    private static Driver driver;

    /**
     * STATIC
     */
    // static constructor
    static {
        dbModel = new SoulDbModel(getPropConn().getDatabase());
        try {
            driver = new com.mysql.jdbc.Driver();
        } catch (SQLException ex) {
            Logger.getLogger(MyQueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Create new SqlQueryBuilder
     *
     * @return SqlQueryBuilder
     */
    public static QueryBuilder getQb() {
        return new SqlQueryBuilder();
    }

    public static DbModel getDbModel() {
        return dbModel;
    }

    public static Driver getDriver() {
        return driver;
    }

    /**
     * INSTANTIABLE
     */
    private EntityModel model;

    public MyQueryHandler(EntityModel model) {
        super(getDriver(), "allowMultiQueries=true");
        this.model = model;
    }

    public MyQueryHandler(EntityModel model, Class rsClass) {
        super(rsClass, getDriver(), "allowMultiQueries=true");
        this.model = model;
    }

    public TableData loadData(String additional, String... searchText) {
        QueryBuilder qb = getQb().select(this.model.getFieldsName(getQb()))
                .from().qbBuildName(this.model.getPath());

        LinkedHashMap<DbId, Entry<EntityModel, LinkedHashMap<DbId.Field, Entry>>> joinTables = this.model.getJoinTables();
        Set<Map.Entry<DbId, Entry<EntityModel, LinkedHashMap<DbId.Field, Entry>>>> l = joinTables.entrySet();
        for (Map.Entry<DbId, Entry<EntityModel, LinkedHashMap<DbId.Field, Entry>>> joins : l) {
            qb.join(SqlQueryBuilder.SqlJoinTypes.INNER)
                    .qbBuildName(((EntityModel) joins.getValue().getKey()).getPath());
        }

        boolean on = false;
        int cnt = 0;
        for (Map.Entry<DbId, Entry<EntityModel, LinkedHashMap<DbId.Field, Entry>>> joins : l) {
            if (!((LinkedHashMap) joins.getValue().getValue()).isEmpty()) {
                Collection<Entry> list = ((LinkedHashMap<DbId.Field, Entry>) joins.getValue().getValue()).values();
                Iterator<Entry> iterator = list.iterator();
                Entry relation = iterator.next();
                for (int i = 0; i < list.size(); i++) {
                    if (relation != null) {
                        // XXX ugly workaround 
                        if (!on) {
                            qb.on();
                            on = true;
                        }

                        qb.qbBuildName(((FieldModel) relation.getKey()).getPath())
                                .qbCompare()
                                .qbBuildName(((FieldModel) relation.getValue()).getPath());

                        try {

                            relation = iterator.next();

                            if (i < list.size() - 1 || cnt < l.size() - 1) {
                                if (relation != null) {
                                    qb.and();
                                }
                            }
                        } catch (Exception e) {
                            relation = null;
                        }
                    }
                }
            }

            cnt++;
        }

        if (searchText.length > 0) {
            if (searchText.length != 1 || !searchText[0].isEmpty()) {
                qb.where(searchText);
            }
        }

        if (additional != null && !additional.isEmpty()) {
            qb.qbAdd(" " + additional);
        }

        return _loadData(qb.toString());
    }

    @Override
    public TableData loadData(String search) {
        return this.loadData(null, search);
    }

    public EntityModel getModel() {
        return model;
    }

    protected void setModel(EntityModel model) {
        this.model = model;
    }

    /**
     * It's not overridden load data
     *
     * @param searchText
     * @return
     */
    protected TableData _loadData(String query) {
        return super.loadData(query);
    }

    public void updateData(TableData tableData, String[] tablesToUpdate) {
        QueryBuilder query = getQb();

        query.qbMerge(buildDeleteQuery(tableData, tablesToUpdate));

        query.qbMerge(buildInsertQuery(tableData, tablesToUpdate));

        query.qbMerge(buildUpdateQuery(tableData, tablesToUpdate));

        String q = query.toString();
        if (!q.isEmpty()) {
            this.execute(q);
        }
    }

    public QueryBuilder buildDeleteQuery(TableData tableData, String[] tablesToUpdate) {
        QueryBuilder query = getQb();
        ArrayList<RecordSet> rmList = tableData.getRemoved();
        if (rmList.isEmpty()) {
            return null;
        }

        for (String table : tablesToUpdate) {
            // get primary key of needed table
            HashSet<DbId.Field> keys = tableData.getPrimaryKeys();

            if (!keys.isEmpty()) {
                query.delete()
                        .from(table);

                int i = 0;
                for (DbId pk : keys) {
                    ArrayList<String> values = new ArrayList<>();
                    FieldModel pkModel = this.getModel().getFieldModel(pk);
                    for (RecordSet rs : rmList) {
                        values.add(rs.getValue(pkModel.getId()).toString());
                    }

                    (i == 0
                            ? query.where().qbBuildName(pkModel.getPath())
                            : query.and().qbBuildName(pkModel.getPath()))
                            .in(values.toArray(new String[values.size()]));

                    i++;
                }

                query.qbCloseQuery();
            }
        }

        return query;
    }

    public QueryBuilder buildInsertQuery(TableData tableData, String[] tablesToUpdate) {
        QueryBuilder query = getQb();
        ArrayList<RecordSet> insertList = tableData.getInserted();
        if (insertList.isEmpty()) {
            return null;
        }

        for (String table : tablesToUpdate) {
            LinkedHashMap<String, Object> list = new LinkedHashMap<>();
            for (RecordSet rs : insertList) {
                for (DbVar var : rs.getRecordValues()) {
                    if (var.getTableName().equals(table)) {
                        Object val = var.getValue();
                        list.put(var.getFieldName(), val != null ? val : null);
                    }
                }
            }

            if (!list.isEmpty()) {
                query.insert()
                        .into(table, list.keySet().toArray(new String[list.size()]))
                        .values(list.values().toArray())
                        .qbCloseQuery();
            }
        }

        return query;
    }

    public QueryBuilder buildUpdateQuery(TableData tableData, String[] tablesToUpdate) {
        ArrayList<RecordSet> upList = tableData.getUpdated();
        if (upList.isEmpty()) {
            return null;
        }

        QueryBuilder query = getQb();

        for (String table : tablesToUpdate) {
            boolean canUpdate = false;

            for (RecordSet rs : upList) {
                QueryBuilder tmpQuery = getQb();
                for (DbVar var : rs.getRecordValues()) {
                    if (var.getTableName().equals(table) && var.isChanged()) {
                        canUpdate = true;
                        Object val = var.getValue();
                        if (val instanceof String) {
                            tmpQuery.qbBuildName(var.getPath()).qbAssign(val.toString());
                        } else {
                            tmpQuery.qbBuildName(var.getPath()).qbAssign(val);
                        }
                    }
                }

                if (canUpdate) {
                    HashSet<DbId.Field> keys = tableData.getPrimaryKeys();

                    if (!keys.isEmpty()) {

                        query.update(table).set().qbMerge(tmpQuery);

                        int i = 0;
                        for (DbId k : keys) {
                            FieldModel pk = this.getModel().getFieldModel(k);

                            Object val = rs.getValue(pk.getId());
                            if (i == 0) {
                                query.where();
                            } else {
                                query.and();
                            }

                            if (val instanceof String) {
                                query.qbBuildName(pk.getPath()).qbCompare(val.toString());
                            } else {
                                query.qbBuildName(pk.getPath()).qbCompare(val);
                            }

                            i++;
                        }

                        query.qbCloseQuery();
                    }
                }
            }
        }

        return query;
    }

    public void replaceData(TableData tableData) {
        //execute()
    }
}

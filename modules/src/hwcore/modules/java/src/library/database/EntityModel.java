/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.database;

import hwcore.modules.java.src.library.common.Entry;
import hwcore.modules.java.src.library.common.MyObject;
import hwcore.modules.java.src.library.database.fielddecorators.CalculatedField;
import hwcore.modules.java.src.library.database.fielddecorators.RelField;
import hwcore.modules.java.src.library.database.querybuilders.QueryBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import system.common.MyQueryHandler;

abstract public class EntityModel extends MyObject implements DbIdentifier {

    private final String tableName;
    // table - ON condition
    private final LinkedHashMap<DbId, Entry<EntityModel, LinkedHashMap<DbId.Field, Entry>>> joinTables;
    private final SchemaModel schemaModel;

    private final LinkedHashMap<DbId, FieldModel> decList = new LinkedHashMap<>();

    protected EntityModel(String tableName, SchemaModel schemaModel) {
        this.tableName = tableName;
        this.schemaModel = schemaModel;
        this.joinTables = new LinkedHashMap<>();
    }

    protected void createFields(FieldModel... fields) {
        this.createFields(Arrays.asList(fields));
    }

    protected void createFields(Collection<FieldModel> fields) {
        for (FieldModel f : fields) {
            decList.put(f.getId(), f);

            if (!f.getEntityModel().getId().equals(this.getId())) {
                joinTables.put(f.getEntityModel().getId(), new Entry(f.getEntityModel(), new LinkedHashMap<DbId.Field, Entry>()));
            }
        }
    }

    public EntityModel mergeFields(EntityModel... models) {
        for (EntityModel model : models) {
            this.createFields(model.getFields().values());
        }

        return this;
    }

    public FieldModel getFieldModel(DbId key) {
        return decList.get(key);
    }

    public String getTableName() {
        return tableName;
    }

    public String[] getFieldsName(QueryBuilder qb) {
        ArrayList<String> names = new ArrayList<>();
        for (FieldModel m : decList.values()) {
            qb.reset();
            names.add(m instanceof CalculatedField
                    ? ((CalculatedField) m).getExpression()
                    : qb.qbBuildName(m.getPath()).toString());
        }

        return names.toArray(new String[names.size()]);
    }

    public SchemaModel getSchemaModel() {
        return schemaModel;
    }

    public LinkedHashMap<DbId, Entry<EntityModel, LinkedHashMap<DbId.Field, Entry>>> getJoinTables() {
        // XXX ugly workaround to be sure that rel fields are calculated correctly in this map
        for (FieldModel f : decList.values()) {
            if (f instanceof RelField) {
                if (((RelField) f).getJoinField().getEntityModel().getId().equals(this.getId())
                        || joinTables.containsKey(((RelField) f).getJoinField().getEntityModel().getId())) {

                    DbId.Table id = f.getEntityModel().getId().equals(this.getId())
                            ? ((RelField) f).getJoinField().getEntityModel().getId()
                            : f.getEntityModel().getId();

                    Entry<EntityModel, LinkedHashMap<DbId.Field, Entry>> e = joinTables.get(id);

                    ((LinkedHashMap<DbId.Field, Entry>) e.getValue())
                            .put(f.getId(), new Entry(f, ((RelField) f).getJoinField()));
                }
            }
        }

        return joinTables;
    }

    @Override
    public String[] getPath() {
        return new String[]{
            this.getSchemaModel().getDbModel().getDbName(),
            this.getSchemaModel().getSchemaName(),
            this.getTableName()};
    }

    @Override
    public DbId.Table getId() {
        return new DbId.Table(
                this.getSchemaModel().getDbModel().getDbName(),
                this.getSchemaModel().getSchemaName(),
                this.getTableName()
        );
    }

    /**
     * Pass a clone of the list, to avoid external changes
     *
     * @return
     */
    public LinkedHashMap<DbId, FieldModel> getFields() {
        return (LinkedHashMap<DbId, FieldModel>) decList.clone();
    }
}

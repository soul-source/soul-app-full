/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.database;

import hwcore.modules.java.src.library.common.StatusVar;

public class DbVar<T> extends StatusVar implements DbIdentifier {

    protected String fieldName, tableName, catalogName, schemaName;
    protected boolean primaryKey;

    public DbVar(Object val, String fieldName, String tableName, String schemaName, String catalogName, boolean primaryKey) {
        super(val);
        this.fieldName = fieldName;
        this.tableName = tableName;
        this.catalogName = catalogName;
        this.schemaName = schemaName;
        this.primaryKey = primaryKey;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    @Override
    public DbId getId() {
        return new DbId.Field(catalogName, schemaName, tableName, fieldName);
    }

    @Override
    public String[] getPath() {
        return new String[]{catalogName, schemaName, tableName, fieldName};
    }
}

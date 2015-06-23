/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.database;

/**
 * Base class for field decorators
 */
public abstract class FieldModel implements DbIdentifier {

    private final String field;
    private final EntityModel entityModel;
    private Object defVal;

    public FieldModel(String field, EntityModel entityModel, Object defVal) {
        this.field = field;
        this.entityModel = entityModel;
        this.defVal = defVal;
    }

    public FieldModel(String field, EntityModel entityModel) {
        this(field, entityModel, null);
    }

    @Override
    public String[] getPath() {
        return new String[]{
            this.entityModel.getSchemaModel().getDbModel().getDbName(),
            this.entityModel.getSchemaModel().getSchemaName(),
            this.entityModel.getTableName(),
            field
        };
    }

    @Override
    public DbId.Field getId() {
        return new DbId.Field(
                this.entityModel.getSchemaModel().getDbModel().getDbName(),
                this.entityModel.getSchemaModel().getSchemaName(),
                this.entityModel.getTableName(),
                field
        );
    }

    public String getFieldName() {
        return field;
    }

    public EntityModel getEntityModel() {
        return entityModel;
    }

    public Object getDefVal() {
        return defVal;
    }

    public void setDefVal(Object defVal) {
        this.defVal = defVal;
    }
}

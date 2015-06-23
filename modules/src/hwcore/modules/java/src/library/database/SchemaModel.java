/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.database;

import java.util.HashMap;

public class SchemaModel {

    private final String schemaName;
    private final DbModel dbModel;

    private final HashMap<String, EntityModel> eList = new HashMap<>();

    public SchemaModel(String schemaName, DbModel dbModel) {
        this.schemaName = schemaName;
        this.dbModel = dbModel;
    }

    public void defineEntity(EntityModel model) {
        eList.put(model.getTableName(), model);
    }

    public String getSchemaName() {
        return schemaName;
    }

    public DbModel getDbModel() {
        return dbModel;
    }
}

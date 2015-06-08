/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */

package hwcore.modules.java.src.library.database;

import java.util.HashMap;

public abstract class DbModel {
    private final String dbName;

    private final HashMap<String, SchemaModel> eList = new HashMap<>();

    public DbModel(String dbName) {
        this.dbName = dbName;
    }

    public void defineEntity(SchemaModel model) {
        eList.put(model.getSchemaName(), model);
    }

    public String getDbName() {
        return dbName;
    }
}

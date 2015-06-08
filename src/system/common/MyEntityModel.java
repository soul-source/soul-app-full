/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */

package system.common;

import hwcore.modules.java.src.library.database.DbModel;
import hwcore.modules.java.src.library.database.EntityModel;
import hwcore.modules.java.src.library.database.SchemaModel;

public class MyEntityModel extends EntityModel {

    /**
     * Schema is empty in mysql
     * @param table 
     */
    public MyEntityModel(String table) {
        super(table,new SchemaModel("", MyQueryHandler.getDbModel()));
    }
    
    public MyEntityModel(String table,DbModel dbModel) {
        super(table,new SchemaModel("", dbModel));
    }
}

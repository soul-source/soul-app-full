/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.database;

import hwcore.modules.java.src.library.common.StringTools;

/**
 * This is the identifier of a single field in a database calculated on its full
 * "path"
 */
public abstract class DbId {

    public static class Field extends DbId {

        public Field(String catalog, String schema, String table, String field) {
            super(catalog, schema, table, field);
        }

        @Override
        public boolean equals(Object obj) {
            return obj != null && obj instanceof Field && this.id.equals(((Field) obj).id);
        }
    }

    public static class Table extends DbId {

        public Table(String catalog, String schema, String table) {
            super(catalog, schema, table);
        }

        @Override
        public boolean equals(Object obj) {
            return obj != null && obj instanceof Table && this.id.equals(((Table) obj).id);
        }
    }

    public final String id;

    public DbId(String... path) {
        this.id = StringTools.join("", path);
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}

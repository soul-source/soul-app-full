/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.database.querybuilders;

import java.util.Map;

public class SqlQueryBuilder extends QueryBuilder {

    public enum SqlJoinTypes implements JoinTypes {

        INNER("INNER"),
        LEFT("LEFT"),
        RIGHT("RIGHT"),
        FULL("FULL");

        private String type;

        private SqlJoinTypes(String type) {
            this.type = type;
        }

        @Override
        public String getType() {
            return type;
        }
    }

    public enum SqlOrderType implements OrderType {

        ASC("ASC"),
        DESC("DESC");
        private String type;

        private SqlOrderType(String type) {
            this.type = type;
        }

        @Override
        public String getType() {
            return type;
        }
    }

    @Override
    public QueryBuilder select(String... args) {
        if (args.length == 0) {
            args = new String[]{"*"};
        }

        return this.qbAdd("SELECT", this.qbValueSep(), args);
    }

    @Override
    public QueryBuilder insert() {
        return this.qbAdd("INSERT", "", "");
    }

    @Override
    public QueryBuilder replace() {
        return this.qbAdd("REPLACE", "", "");
    }

    @Override
    public QueryBuilder delete() {
        return this.qbAdd("DELETE", "", "");
    }

    @Override
    public QueryBuilder update(String... args) {
        return this.qbAdd("UPDATE", this.qbValueSep(), args);
    }

    public QueryBuilder set(String... args) {
        return this.qbAdd("SET", this.qbValueSep(), args);
    }

    @Override
    public QueryBuilder from(String... args) {
        return this.qbAdd("FROM", this.qbValueSep(), args);
    }

    @Override
    public QueryBuilder join(JoinTypes type, String... args) {
        int i = 0;

        String table = args != null && args.length > 0 ? args[0] : "";

        do {

            this.qbAdd(type.getType(), "", "")
                    .qbAdd("JOIN", "", table);
            i++;

            if (i < args.length) {
                table = args[i];
            } else {
                break;
            }

        } while (true);

        return this;
    }

    @Override
    public QueryBuilder on(String... args) {
        return this.qbAdd("ON", " AND ", args);
    }

    @Override
    public QueryBuilder into(String tableName, String... args) {
        return this.qbAdd("INTO", "", tableName).qbAdd("(").qbAdd("", this.qbValueSep(), args).qbAdd(")");
    }

    @Override
    public QueryBuilder into(String tableName) {
        return this.qbAdd("INTO", "", tableName);
    }

    @Override
    public QueryBuilder values(String... args) {
        return this.qbAdd("VALUES").qbAdd("(").qbAdd("", this.qbValueSep(), args).qbAdd(")");
    }

    @Override
    public QueryBuilder values(Object... args) {
        String sValues[] = new String[args.length];

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String) {
                sValues[i] = "'" + args[i] + "'";
            } else if (args[i] instanceof java.util.Date) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                sValues[i] = "'" + sdf.format(args[i]) + "'";
            } else if (args[i] == null) {
                sValues[i] = "NULL";
            } else {
                sValues[i] = args[i].toString();
            }
        }

        return this.values(sValues);
    }

    /**
     * Consecutive args will be considered in AND cascade
     *
     * @param args
     * @return
     */
    @Override
    public QueryBuilder where(String... args) {
        return this.qbAdd("WHERE", " AND ", args);
    }

    /**
     * Consecutive args will be considered in AND cascade
     *
     * @param args
     * @return
     */
    @Override
    public QueryBuilder having(String... args) {
        return this.qbAdd("HAVING", " AND ", args);
    }

    @Override
    public QueryBuilder groupBy(String... args) {
        return this.qbAdd("GROUP BY", this.qbValueSep(), args);
    }

    @Override
    public QueryBuilder orderBy(String... args) {
        return this.qbAdd("ORDER BY", this.qbValueSep(), args);
    }

    @Override
    public QueryBuilder limit(int rowCount, int offset) {
        return this.qbAdd("LIMIT", "", rowCount).qbAdd("OFFSET", "", offset);
    }

    @Override
    public QueryBuilder or(String... args) {
        return this.qbAdd("OR", " OR ", args);
    }

    @Override
    public QueryBuilder and(String... args) {
        return this.qbAdd("AND", " AND ", args);
    }

    @Override
    public QueryBuilder not(String condition) {
        return this.qbAdd("NOT", "", condition);
    }

    @Override
    public <T> QueryBuilder between(T startVal, T endVal) {
        return this.qbAdd("BETWEEN", "", qbNormalizeVal(startVal)).and().qbAdd("", "", qbNormalizeVal(endVal));
    }

    /**
     *
     * @param args passed args will be included in brackets separated by comma
     * @return
     */
    @Override
    public QueryBuilder in(String... args) {
        return this.qbAdd("IN").qbAdd("(").qbAdd("", this.qbValueSep(), args).qbAdd(")");
    }

    @Override
    public QueryBuilder qbCompare() {
        return this.qbAdd("=");
    }

    // use polymorphism as "switcher" for search condition
    // case String:
    @Override
    public <T extends String> QueryBuilder qbCompare(T compareWith) {
        compareWith = (T) (compareWith.isEmpty() ? "" : qbNormalizeVal(compareWith));
        return this.qbAdd("LIKE", "", compareWith);
    }

    // default:
    @Override
    public <T> QueryBuilder qbCompare(T compareWith) {
        return this.qbCompare().qbAdd("", "", compareWith);
    }

    @Override
    public <T> QueryBuilder qbCompare(Map.Entry<String, T>... values) {
        for (int i = 0; i < values.length; i++) {
            Map.Entry<String, T> entry = values[i];
            this.qbAdd(entry.getKey()).qbCompare(entry.getValue());
            if (i < values.length - 1) {
                this.qbSep();
            }
        }

        return this;
    }

    @Override
    public <T> QueryBuilder qbAssign() {
        return this.qbAdd("=", "", "");
    }

    @Override
    public <T extends String> QueryBuilder qbAssign(T assignVal) {
        assignVal = (T) (assignVal.isEmpty() ? "" : qbNormalizeVal(assignVal));
        return this.qbAdd("=", "", assignVal);
    }

    @Override
    public <T> QueryBuilder qbAssign(T assignVal) {
        return this.qbAssign().qbAdd("", "", assignVal);
    }

    @Override
    public <T> QueryBuilder qbAssign(Map.Entry<String, T>... values) {
        for (int i = 0; i < values.length; i++) {
            Map.Entry<String, T> entry = values[i];
            this.qbAdd(entry.getKey()).qbAssign(entry.getValue());
            if (i < values.length - 1) {
                this.qbSep();
            }
        }

        return this;
    }

    @Override
    public QueryBuilder qbBuildName(String... names) {
        return this.qbAdd("`").qbAdd("", "`.`", names).qbAdd("`");
    }

    @Override
    public QueryBuilder qbCloseQuery() {
        return this.qbAdd(";", "", "");
    }

    @Override
    public String qbValueSep() {
        return ",";
    }

    @Override
    public Object qbNormalizeVal(Object val) {
        if (val == null) {
            return null;
        }

        if (val instanceof String) {
            String string=(String)val;
            if (string.isEmpty()) {
                return "";
            }

            //string = string.replace("_", "\\_");
            //string = string.replace("%", "\\%");

            return "'"+string+"'";
        } else {
            return val;
        }
    }

}

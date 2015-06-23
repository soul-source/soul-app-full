/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.database.querybuilders;

import hwcore.modules.java.src.library.common.If;
import hwcore.modules.java.src.library.common.IfIface;
import hwcore.modules.java.src.library.common.StringTools;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple sql query builder ( beta ) Methods prefixed with "qb" are class tools
 * and not related to sql syntax
 */
public abstract class QueryBuilder implements IfIface {

    public interface JoinTypes {

        public String getType();
    }

    public interface OrderType {

        public String getType();
    }

    private String query;

    public QueryBuilder(String query) {
        this.query = query;
    }

    public QueryBuilder() {
        this("");
    }

    protected static <T extends QueryBuilder> QueryBuilder init(Class<T> qb) {
        try {
            return qb.getConstructor(String.class).newInstance("");
        } catch (Exception ex) {
            Logger.getLogger(QueryBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /*
     Experimental
     */
    public static String compactSelect(QueryBuilder qb, String[] select, String[] from, String[] where, String[] groupBy, String[] having, String[] orderBy, int limit[]) {
        return qb.select(select).from(from).where(where).groupBy(groupBy).having(having).limit(limit[0], limit[1]).toString();
    }

    public abstract QueryBuilder select(String... args);

    public abstract QueryBuilder insert();

    public abstract QueryBuilder replace();

    public abstract QueryBuilder delete();

    public abstract QueryBuilder update(String... args);

    public abstract QueryBuilder set(String... args);

    public abstract QueryBuilder from(String... args);

    public abstract QueryBuilder join(JoinTypes type, String... args);

    public abstract QueryBuilder on(String... args);

    public abstract QueryBuilder into(String tableName, String... args);

    public abstract QueryBuilder into(String tableName);

    public abstract QueryBuilder values(Object... args);

    public abstract QueryBuilder in(String... args);

    /**
     * Consecutive args will be considered in AND cascade
     *
     * @param args
     * @return
     */
    public abstract QueryBuilder where(String... args);

    /**
     * Consecutive args will be considered in AND cascade
     *
     * @param args
     * @return
     */
    public abstract QueryBuilder having(String... args);

    public abstract QueryBuilder groupBy(String... args);

    public abstract QueryBuilder orderBy(String... args);

    public abstract QueryBuilder limit(int rowCount, int offset);

    public abstract QueryBuilder or(String... args);

    public abstract QueryBuilder and(String... args);
    
    public abstract QueryBuilder not(String condition);
    
    public abstract <T> QueryBuilder between(T startVal,T endVal);

    public abstract <T> QueryBuilder qbCompare();

    public abstract <T extends String> QueryBuilder qbCompare(T searchVal);

    public abstract <T> QueryBuilder qbCompare(T searchVal);

    public abstract <T> QueryBuilder qbCompare(Map.Entry<String, T>... values);

    public abstract <T> QueryBuilder qbAssign();

    public abstract <T extends String> QueryBuilder qbAssign(T assignVal);

    public abstract <T> QueryBuilder qbAssign(T assignVal);

    public abstract <T> QueryBuilder qbAssign(Map.Entry<String, T>... values);

    public abstract QueryBuilder qbCloseQuery();

    /**
     * Build column/table names using separators
     *
     * @return
     */
    public abstract QueryBuilder qbBuildName(String... names);

    public abstract String qbValueSep();
    
    public abstract <T> T qbNormalizeVal(T val);

    /*
     * Utility methods
     *
     *
     */
    public void reset() {
        this.query="";
    }
    
    
    /**
     * Special method to add information to the query parts
     *
     * @param string
     * @return
     */
    public QueryBuilder qbAdd(String[] string) {
        return this.qbAdd("", "", string);
    }

    public QueryBuilder qbAdd(String string) {
        return this.qbAdd("", "", string);
    }

    /**
     * Special method to add information to the query parts
     *
     */
    public <T> QueryBuilder qbAdd(String command, String separator, T... args) {
        if (!command.isEmpty()) {
            this.query += " " + command + " ";
        }

        if (args.length > 0) {
            String arg = StringTools.join(separator, args);
            if (!arg.isEmpty()) {
                query += arg;
            }
        }

        return this;
    }

    public QueryBuilder qbOrderType(OrderType type) {
        return this.qbAdd(type.getType(), "", "");
    }

    public QueryBuilder qbSep() {
        return this.qbAdd(qbValueSep());
    }

    public QueryBuilder qbMerge(QueryBuilder toMerge) {
        if (toMerge != null) {
            this.qbAdd(toMerge.toString());
        }
        return this;
    }

    //public QueryBuilder surround(String )
    @Override
    public String toString() {
        return query;
    }

    @Override
    public If.Conditions _if(Object... _this) {
        return If.condition(_this);
    }
}

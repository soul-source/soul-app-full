/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.database;

import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class QueryHandler extends DbConnection {

    protected Class rsClass;
    private final Driver driver;
    private final String connQuery;

    public QueryHandler(Driver driver, String connQuery) {
        this(RecordSet.class, driver, connQuery);
    }

    public <T extends RecordSet> QueryHandler(Class<T> rsClass, Driver driver, String connQuery) {
        super();
        this.rsClass = rsClass;
        this.driver = driver;
        this.connQuery = connQuery;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        releaseAll();
    }

    public Class getRsClass() {
        return rsClass;
    }

    public TableData loadData(String query) {
        execute(query);
        try {
            return new TableData(this);
        } catch (SQLException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ResultSet execute(String query) {
        System.out.println(query);
        try {
            this.conn = this.startConn(propConn.getDatabase(), this.driver, this.connQuery);

            prepStat = conn.prepareStatement(query);
            if (prepStat.execute()) {
                return prepStat.getResultSet();
            }

            return null;
        } catch (SQLException ex) {
            Logger logger = Logger.getLogger(QueryHandler.class.getName());
            logger.log(Level.SEVERE, query);
            logger.log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public PreparedStatement executeStatement(String query) {
        System.out.println(query);
        try {
            this.conn = this.startConn(propConn.getDatabase(), this.driver, this.connQuery);

            prepStat = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            prepStat.execute();
            return prepStat;
        } catch (SQLException ex) {
            Logger logger = Logger.getLogger(QueryHandler.class.getName());
            logger.log(Level.SEVERE, query);
            logger.log(Level.SEVERE, null, ex);
        }

        return null;
    }
}

/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConnection {

    public static PropConnection propConn = new PropConnection();

    public Connection conn = null;
    public PreparedStatement prepStat = null;

    /**
     * Apre una connessione al database
     *
     * @param dbName il nome del database a cui collegarsi
     * @return l'oggetto Connection appena aperto
     */
    public Connection startConn(String dbName, Driver driver, String query) {
        Connection c = null;
        try {
            DriverManager.registerDriver(driver);

            c = DriverManager.getConnection(propConn.getConnection(dbName, query), propConn.getUsername(), propConn.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    /**
     * Chiude le connessioni e annulla i puntatori
     *
     */
    public void releaseAll() {
        if (prepStat != null) {
            try {
                prepStat.close();
            } catch (SQLException sqlEx) {
                System.out.println("SQLException: " + sqlEx.getMessage());
            }

            prepStat = null;
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException sqlEx) {
                // Ignore
            }

            conn = null;
        }
    }

    public static PropConnection getPropConn() {
        return propConn;
    }

    public static void setPropConn(PropConnection pconn) {
        propConn = pconn;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public PreparedStatement getPrepStat() {
        return prepStat;
    }

    public void setPrepStat(PreparedStatement prepStat) {
        this.prepStat = prepStat;
    }
}

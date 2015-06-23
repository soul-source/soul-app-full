package hwcore.modules.java.src.library.database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class connects to a database and dumps all the tables and contents out
 * to stdout in the form of a set of SQL executable statements
 */
public class Db2Sql {

    /**
     * Dump the whole database to an SQL string
     */
    public static void dumpDB(ArrayList<String> tab, Connection dbConn, String filePath) {

        String columnNameQuote = "`";
        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new FileWriter(filePath));
            DatabaseMetaData dbMetaData = dbConn.getMetaData();
            StringBuffer result;
            ResultSet rs = dbMetaData.getTables(null, null, null, new String[]{"TABLE"});
            if (!rs.next()) {
                System.err.println("Unable to find any tables");
                rs.close();
            } else {
                // Right, we have some tables, so we can go to work.
                // the details we have are
                // TABLE_CAT String => table catalog (may be null)
                // TABLE_SCHEM String => table schema (may be null)
                // TABLE_NAME String => table name
                // TABLE_TYPE String => table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
                // REMARKS String => explanatory comment on the table
                // TYPE_CAT String => the types catalog (may be null)
                // TYPE_SCHEM String => the types schema (may be null)
                // TYPE_NAME String => type name (may be null)
                // SELF_REFERENCING_COL_NAME String => name of the designated "identifier" column of a typed table (may be null)
                // REF_GENERATION String => specifies how values in SELF_REFERENCING_COL_NAME are created. Values are "SYSTEM", "USER", "DERIVED". (may be null)
                // We will ignore the schema and stuff, because people might want to import it somewhere else
                // We will also ignore any tables that aren't of type TABLE for now.
                // We use a do-while because we've already caled rs.next to see if there are any rows
                do {
                    result = new StringBuffer();
                    String tableName = rs.getString("TABLE_NAME");
                    if (tab != null && !tab.contains(tableName)) {
                        continue;
                    }

                    String tableType = rs.getString("TABLE_TYPE");
                    if ("TABLE".equalsIgnoreCase(tableType)) {
                        result.append("\n\n-- " + tableName);
                        result.append("\nCREATE TABLE " + columnNameQuote + tableName + columnNameQuote + " (\n");
                        ResultSet tableMetaData = dbMetaData.getColumns(null, null, tableName, "%");
                        boolean firstLine = true;
                        while (tableMetaData.next()) {
                            if (firstLine) {
                                firstLine = false;
                            } else {
                                // If we're not the first line, then finish the previous line with a comma
                                result.append(",\n");
                            }
                            String columnName = tableMetaData.getString("COLUMN_NAME");
                            String columnType = tableMetaData.getString("TYPE_NAME");
                            // WARNING: this may give daft answers for some types on some databases (eg JDBC-ODBC link)
                            int columnSize = tableMetaData.getInt("COLUMN_SIZE");
                            String nullable = tableMetaData.getString("IS_NULLABLE");
                            String nullString = "NULL";
                            if ("NO".equalsIgnoreCase(nullable)) {
                                nullString = "NOT NULL";
                            }
                            result.append("    " + columnNameQuote + columnName + columnNameQuote + " " + columnType + " (" + columnSize + ")" + " " + nullString);
                        }
                        tableMetaData.close();

                        // Now we need to put the primary key constraint
                        try {
                            ResultSet primaryKeys = dbMetaData.getPrimaryKeys(null, null, tableName);
                            // What we might get:
                            // TABLE_CAT String => table catalog (may be null)
                            // TABLE_SCHEM String => table schema (may be null)
                            // TABLE_NAME String => table name
                            // COLUMN_NAME String => column name
                            // KEY_SEQ short => sequence number within primary key
                            // PK_NAME String => primary key name (may be null)
                            String primaryKeyName = null;
                            StringBuffer primaryKeyColumns = new StringBuffer();
                            while (primaryKeys.next()) {
                                String thisKeyName = primaryKeys.getString("PK_NAME");
                                if ((thisKeyName != null && primaryKeyName == null)
                                        || (thisKeyName == null && primaryKeyName != null)
                                        || (thisKeyName != null && !thisKeyName.equals(primaryKeyName))
                                        || (primaryKeyName != null && !primaryKeyName.equals(thisKeyName))) {
                                    // the keynames aren't the same, so output all that we have so far (if anything)
                                    // and start a new primary key entry
                                    if (primaryKeyColumns.length() > 0) {
                                        // There's something to output
                                        result.append(",\n    PRIMARY KEY ");
                                        if (primaryKeyName != null) {
                                            result.append(primaryKeyName);
                                        }
                                        result.append("(" + primaryKeyColumns.toString() + ")");
                                    }
                                    // Start again with the new name
                                    primaryKeyColumns = new StringBuffer();
                                    primaryKeyName = thisKeyName;
                                }
                                // Now append the column
                                if (primaryKeyColumns.length() > 0) {
                                    primaryKeyColumns.append(", ");
                                }
                                primaryKeyColumns.append(primaryKeys.getString("COLUMN_NAME"));
                            }
                            if (primaryKeyColumns.length() > 0) {
                                // There's something to output
                                result.append(",\n    PRIMARY KEY ");
                                if (primaryKeyName != null) {
                                    result.append(primaryKeyName);
                                }
                                result.append(" (" + primaryKeyColumns.toString() + ")");
                            }
                        } catch (SQLException e) {
                            // NB you will get this exception with the JDBC-ODBC link because it says
                            // [Microsoft][ODBC Driver Manager] Driver does not support this function
                            System.err.println("Unable to get primary keys for table " + tableName + " because " + e);
                        }

                        result.append("\n);\n");

                        // Right, we have a table, so we can go and dump it
                        dumpTable(dbConn, result, tableName);
                        out.write(result.toString());
                    }
                } while (rs.next());
                rs.close();
            }
            dbConn.close();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Db2Sql.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
        return;
    }

    /**
     * dump this particular table to the string buffer
     */
    private static void dumpTable(Connection dbConn, StringBuffer result, String tableName) {
        try {
            // First we output the create table stuff
            PreparedStatement stmt = dbConn.prepareStatement("SELECT * FROM " + tableName);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Now we can output the actual data
            result.append("\n\n-- Data for " + tableName + "\n");
            while (rs.next()) {
                result.append("INSERT INTO " + tableName + " VALUES (");
                for (int i = 0; i < columnCount; i++) {
                    if (i > 0) {
                        result.append(", ");
                    }
                    Object value = rs.getObject(i + 1);
                    if (value == null) {
                        result.append("NULL");
                    } else {
                        String outputValue = value.toString();
                        // 4 backslash because also replaceall uses regular expression itself
                        outputValue = "'" + outputValue.replaceAll("'", "\\\\'") + "'";
                        result.append(outputValue);
                    }
                }
                result.append(");\n");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Unable to dump table " + tableName + " because: " + e);
        }
    }
}

package models;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * MetaDataDAO - MetaData Data Access Object
 */
public class DBMetaDataDAO {

    public static final int TABLE_NAME_COL = 3;
    public static final int COLUMN_NAME_COL = 4;


    /**
     * Returns a list of maps.
     * Each map is a table in the database and its value
     * is a list of all the columns found in that table, where
     * each column in the table maps to its SQL datatype as a string.
     */
    public static Map<String, List<Map<String, String>>> getSchema() {

        Connection connection = null;
        ResultSet tables = null;
        ResultSet columns = null;
        Map<String, List<Map<String, String>>> schemaMap = new HashMap<String, List<Map<String, String>>>();

        try {

            connection = DBConnectionManager.getInstance().getConnection();

            // Get all the tables in the database.
            tables = connection.getMetaData().getTables(null, null, null, null);
            while (tables.next()) {
                List<Map<String, String>> columnsInTable = new ArrayList<Map<String, String>>();
                schemaMap.put(tables.getString("TABLE_NAME"), columnsInTable);
            }

            // Get all of the columns for each found table.
            for (Map.Entry<String, List<Map<String, String>>> tableToColumns : schemaMap.entrySet()) {
                columns = connection.getMetaData().getColumns(null, null, tableToColumns.getKey(), null);
                while (columns.next()) {
                    Map<String, String> columnToDataTypeMap = new HashMap<String, String>();
                    columnToDataTypeMap.put(columns.getString("COLUMN_NAME"), columns.getString("TYPE_NAME"));
                    tableToColumns.getValue().add(columnToDataTypeMap);
                }
            }

            return schemaMap;

        } catch (SQLException e) {
            return Collections.emptyMap();
        } finally {
            try {
                tables.close();
            } catch (SQLException e) { }
            try {
                columns.close();
            } catch (SQLException e) { }
        }
    }

    // TODO: Maybe make this print out in a nice format?
    public static void printDBSchema() {
        System.out.println();
        Map<String, List<Map<String, String>>> dbSchema = DBMetaDataDAO.getSchema();
        for (Map.Entry<String, List<Map<String, String>>> tableSchema : dbSchema.entrySet()) {
            System.out.println("Table: " + tableSchema.getKey());
            System.out.println("Columns: " + tableSchema.getValue().toString());
            System.out.println();
        }
    }
}

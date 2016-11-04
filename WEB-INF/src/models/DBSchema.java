package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kbendick on 5/4/16.
 */
public class DBSchema {


    /* Inner class for Table */
    public static class Table {

        public String name;
        public List<String> columns;

        public Table() {
            this.name = null;
            this.columns = new ArrayList<String>();
        }

        public Table(String name) {
            this.name = name;
            this.columns = new ArrayList<String>();
        }

        public List<String> getColumns() { return this.columns; }

        public String getName() { return this.name; }

        public void setName(String name) { this.name = name; }

        public void addColumn(String column) {
            this.columns.add(column);
        }
    }

    // Attributes
    List<Table> tableList;

    public DBSchema() {
        this.tableList = new ArrayList<Table>();
    }

    public void addTable(String tableName) {
        this.tableList.add(new Table(tableName));
    }

    public List<Table> getTableList() { return this.tableList; }

//    public static DBSchema buildDBSchema(Map<String, List<Map<String, String>>> schemaMap) {
//
//        DBSchema dbSchema = new DBSchema();
//        for (Map.Entry<String, List<Map<String, String>>> tableSchema : schemaMap.entrySet()) {
//
//            String tableName = tableSchema.getKey();
//            System.out.println("Table: " + tableName);
//            System.out.println("Columns: " + tableSchema.getValue().toString());
//            System.out.println();
//
//            dbSchema.addTable(tableSchema.getKey());
//        }
//
//        return null;
//    }
}

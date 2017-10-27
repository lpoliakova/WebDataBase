package client.utils;

import database.*;

import java.util.Map;
import java.util.Set;

/**
 * Created by oradchykova on 10/27/17.
 */
public class ButtonsLogic {

    public static void createSchema(String name) {
        //collect all values from table creation page
        Schema schema = Schema.createSchema(name); //TODO: distribute
        WorkingSet.setCurrentSchema(schema);
        //go to table page, close schema page
    }

    public static void loadSchema(String name) {
        //collect all values from table creation page
        Schema schema = Schema.loadSchema(name); //TODO: distribute
        WorkingSet.setCurrentSchema(schema);
        //go to table page, close schema page
    }

    public static void deleteSchema(String name) {
        //collect all values from table creation page
        Schema.deleteSchema(name); //TODO: distribute //TODO: fix Schema
        WorkingSet.setCurrentSchema(null);
        //go to table page, close schema page
    }

    public static void leaveSchema() {
        WorkingSet.setCurrentSchema(null);
        //go to schema page
    }

    public static void createTable(String tableName, Set<Attribute> attributes) {
        //collect all values from table creation page
        Schema schema = WorkingSet.getCurrentSchema();
        Table table = new Table(tableName, attributes);
        schema.addTable(tableName, table); //TODO: distribute
        WorkingSet.setCurrentTable(table);
        //refresh table page, close table creation page
    }

    public static void loadTable(String tableName) {
        //collect all values from choose table page
        Schema schema = WorkingSet.getCurrentSchema();
        Table table = schema.readTableFromDatabase(tableName); //TODO: distribute
        WorkingSet.setCurrentTable(table);
        //refresh table page, close choose table page
    }

    public static void saveTable() {
        Schema schema = WorkingSet.getCurrentSchema();
        Table table = WorkingSet.getCurrentTable();
        schema.writeTableToDatabase(table.getName()); //TODO: distribute
        //refresh table page
    }

    public static void deleteTable() {
        Schema schema = WorkingSet.getCurrentSchema();
        Table table = WorkingSet.getCurrentTable();
        schema.deleteTable(table.getName()); //TODO: distribute
        WorkingSet.setCurrentTable(null);
        //refresh table page
    }

    public static void addEntry(Map<Attribute, String> values) {
        //collect all values from entry page
        Entry entry = Entry.create(values);
        Table table = WorkingSet.getCurrentTable();
        table.addEntry(entry);
        //refresh table page, close entry page
    }

    public static void deleteEntry(Map<Attribute, String> values) {
        //collect all values from entry page
        Entry entry = Entry.create(values);
        Table table = WorkingSet.getCurrentTable();
        table.deleteEntry(entry);
        //refresh table page, close entry page
    }

    public static Attribute addAtribute(String name, DatabaseTypes type) {
        //collect all values from table create page
        return new Attribute(name, type);
        //refresh create table page
    }
}

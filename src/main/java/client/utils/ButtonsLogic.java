package client.utils;

import client.view.StartFrame;
import client.view.TableFrame;
import database.*;

import java.awt.*;
import java.util.Map;
import java.util.Set;

/**
 * Created by oradchykova on 10/27/17.
 */
public class ButtonsLogic {

    public static void createSchema(String name) {
        Schema schema = Schema.createSchema(name); //TODO: distribute
        WorkingSet.setCurrentSchema(schema);
        EventQueue.invokeLater(TableFrame::new);
    }

    public static void loadSchema(String name) {
        Schema schema = Schema.loadSchema(name); //TODO: distribute
        WorkingSet.setCurrentSchema(schema);
        EventQueue.invokeLater(TableFrame::new);
    }

    public static void deleteSchema(String name) {
        Schema.deleteSchema(name); //TODO: distribute
        WorkingSet.setCurrentSchema(null);
        EventQueue.invokeLater(StartFrame::new);
    }

    public static void leaveSchema() {
        WorkingSet.setCurrentSchema(null);
        EventQueue.invokeLater(StartFrame::new);
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

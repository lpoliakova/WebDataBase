package client.utils;

import client.view.StartFrame;
import client.view.TableFrame;
import shared.database.*;

import java.awt.*;
import java.util.Map;
import java.util.Set;

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
        Schema schema = WorkingSet.getCurrentSchema();
        Table table = new Table(tableName, attributes);
        schema.addTable(tableName, table); //TODO: distribute
        WorkingSet.setCurrentTable(table);
        EventQueue.invokeLater(TableFrame::new);
    }

    public static void loadTable(String tableName) {
        Schema schema = WorkingSet.getCurrentSchema();
        Table table = schema.readTableFromDatabase(tableName); //TODO: distribute
        WorkingSet.setCurrentTable(table);
        EventQueue.invokeLater(TableFrame::new);
    }

    public static void saveTable() {
        Schema schema = WorkingSet.getCurrentSchema();
        Table table = WorkingSet.getCurrentTable();
        schema.writeTableToDatabase(table.getName()); //TODO: distribute
    }

    public static void deleteTable() {
        Schema schema = WorkingSet.getCurrentSchema();
        Table table = WorkingSet.getCurrentTable();
        schema.deleteTable(table.getName()); //TODO: distribute
        WorkingSet.setCurrentTable(null);
        EventQueue.invokeLater(TableFrame::new);
    }

    public static void addEntry(Map<Attribute, String> values) {
        //collect all values from entry page
        Entry entry = Entry.create(values);
        Table table = WorkingSet.getCurrentTable();
        table.addEntry(entry);
        EventQueue.invokeLater(TableFrame::new);
        //close entry page
    }

    public static void deleteEntry(Map<Attribute, String> values) {
        //collect all values from entry page
        Entry entry = Entry.create(values);
        Table table = WorkingSet.getCurrentTable();
        table.deleteEntry(entry);
        EventQueue.invokeLater(TableFrame::new);
        //close entry page
    }

    public static Attribute addAttribute(String name, DatabaseTypes type) {
        return new Attribute(name, type);
        //refresh create table page
    }
}

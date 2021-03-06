package client.utils;

import client.view.StartFrame;
import client.view.TableFrame;
import client.view.TwoTablesFrame;
import shared.*;

import java.awt.*;
import java.util.Map;
import java.util.Set;

public class ButtonsLogic {
//TODO: handle exceptions

    public static void createSchema(String name) {
        Schema schema = new Schema(name);
        WorkingSet.getConnection().createSchema(name);
        WorkingSet.setCurrentSchema(schema);
        EventQueue.invokeLater(TableFrame::new);
    }

    public static void loadSchema(String name) {
        Schema schema = WorkingSet.getConnection().loadSchema(name);
        WorkingSet.setCurrentSchema(schema);
        EventQueue.invokeLater(TableFrame::new);
    }

    public static void deleteSchema(String name) {
        WorkingSet.getConnection().deleteSchema(name);
        WorkingSet.setCurrentSchema(null);
        EventQueue.invokeLater(StartFrame::new);
    }

    public static void leaveSchema() {
        WorkingSet.setCurrentSchema(null);
        WorkingSet.setCurrentTable(null);
        WorkingSet.setCurrentOtherTable(null);
        EventQueue.invokeLater(StartFrame::new);
    }

    public static void createTable(String tableName, Set<Attribute> attributes) {
        Schema schema = WorkingSet.getCurrentSchema();
        Table table = new Table(tableName, attributes);
        schema.addTable(tableName, table);
        WorkingSet.getConnection().writeTable(schema.getName(), table);
        WorkingSet.setCurrentTable(table);
        EventQueue.invokeLater(TableFrame::new);
    }

    public static void loadTable(String tableName) {
        Schema schema = WorkingSet.getCurrentSchema();
        Table table = schema.getTable(tableName);
        if (WorkingSet.isOneTable()) {
            WorkingSet.setCurrentTable(table);
            EventQueue.invokeLater(TableFrame::new);
        } else {
            if (WorkingSet.isFirstTable()) {
                WorkingSet.setCurrentTable(table);
                EventQueue.invokeLater(TwoTablesFrame::new);
            } else {
                WorkingSet.setCurrentOtherTable(table);
                EventQueue.invokeLater(TwoTablesFrame::new);
            }
        }
    }

    public static void saveTable(boolean first) {
        Schema schema = WorkingSet.getCurrentSchema();
        Table table;
        if (first) {
            table = WorkingSet.getCurrentTable();
        } else {
            table = WorkingSet.getCurrentOtherTable();
        }
        WorkingSet.getConnection().writeTable(schema.getName(), table);
    }

    public static void deleteTable() {
        Schema schema = WorkingSet.getCurrentSchema();
        Table table = WorkingSet.getCurrentTable();
        schema.deleteTable(table.getName());
        WorkingSet.getConnection().deleteTable(schema.getName(), table.getName());
        WorkingSet.setCurrentTable(null);
        EventQueue.invokeLater(TableFrame::new);
    }

    public static void subtractTables() {
        Table firstTable = WorkingSet.getCurrentTable();
        Table secondTable = WorkingSet.getCurrentOtherTable();
        Table newTable = Table.subtractTables("subtraction_of_" + firstTable.getName() + "_and_" + secondTable.getName(),
                firstTable, secondTable);
        WorkingSet.setCurrentTable(newTable);
        WorkingSet.setCurrentOtherTable(null);
        EventQueue.invokeLater(TableFrame::new);
    }

    public static void intersectTables() {
        Table firstTable = WorkingSet.getCurrentTable();
        Table secondTable = WorkingSet.getCurrentOtherTable();
        Table newTable = Table.intersectTables("intersection_of_" + firstTable.getName() + "_and_" + secondTable.getName(),
                firstTable, secondTable);
        WorkingSet.setCurrentTable(newTable);
        WorkingSet.setCurrentOtherTable(null);
        EventQueue.invokeLater(TableFrame::new);
    }

    public static void addEntry(Map<Attribute, String> values) {
        Entry entry = Entry.create(values);
        Table table = WorkingSet.getCurrentTable();
        table.addEntry(entry);
        EventQueue.invokeLater(TableFrame::new);
    }

    public static void deleteEntry(Map<Attribute, String> values) {
        Entry entry = Entry.create(values);
        Table table = WorkingSet.getCurrentTable();
        table.deleteEntry(entry);
        EventQueue.invokeLater(TableFrame::new);
    }

    public static Attribute addAttribute(String name, DatabaseTypes type) {
        return new Attribute(name, type);
    }
}

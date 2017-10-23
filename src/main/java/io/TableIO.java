package io;

import database.Table;

import java.io.File;
import java.io.IOException;

public class TableIO {
    private static final XmlInput xmlInput = new XmlInput();
    private static final XmlOutput xmlOutput = new XmlOutput();

    public static Table readTable(String schemaName, String tableName) throws IOException {
        File schema = new File(schemaName);
        if (!schema.exists()) {
            throw new IllegalArgumentException("schema with name " + schemaName + " does not exist");
        }
        File table = FileIO.convertToFile(schemaName, tableName);
        if (!table.exists()) {
            throw new IllegalArgumentException("table with name" + tableName + " does not exist");
        }
        return xmlInput.readFile(table);
    }

    public static void writeTable(String schemaName, String tableName, Table table) throws IOException {
        File schema = new File(schemaName);
        if (!schema.exists()) {
            throw new IllegalArgumentException("schema with name " + schemaName + " does not exist");
        }
        xmlOutput.writeFile(FileIO.convertToFile(schemaName, tableName), table);
    }

    public static void deleteTable(String schemaName, String tableName) {
        File schema = new File(schemaName);
        if (!schema.exists()) {
            throw new IllegalArgumentException("schema with name " + schemaName + " does not exist");
        }
        File table = FileIO.convertToFile(schemaName, tableName);
        if (!table.exists()) {
            throw new IllegalArgumentException("table with name" + tableName + " does not exist");
        }
        FileIO.deleteFile(table);
    }
}

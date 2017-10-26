package io;

import database.Table;

import java.io.File;
import java.io.IOException;

public class TableIO {
    static final String TABLE_ENDING = ".xml";
    private static final XmlInput xmlInput = new XmlInput();
    private static final XmlOutput xmlOutput = new XmlOutput();

    public static Table readTable(String schemaName, String tableName) throws IOException {
        File schema = new File(schemaName);
        if (!schema.exists()) {
            throw new IllegalArgumentException("schema with name " + schemaName + " does not exist");
        }
        File table = convertToFile(schemaName, tableName);
        if (!table.exists()) {
            throw new IllegalArgumentException("table with name " + tableName + " does not exist");
        }
        return xmlInput.readFile(table);
    }

    public static void writeTable(String schemaName, String tableName, Table table) throws IOException {
        File schema = new File(schemaName);
        if (!schema.exists()) {
            throw new IllegalArgumentException("schema with name " + schemaName + " does not exist");
        }
        xmlOutput.writeFile(convertToFile(schemaName, tableName), table);
    }

    public static void deleteTable(String schemaName, String tableName) {
        File schema = new File(schemaName);
        if (!schema.exists()) {
            throw new IllegalArgumentException("schema with name " + schemaName + " does not exist");
        }
        File table = convertToFile(schemaName, tableName);
        if (!table.exists()) {
            throw new IllegalArgumentException("table with name" + tableName + " does not exist");
        }
        FileIO.deleteFile(table);
    }

    private static File convertToFile(String schemaName, String tableName) {
        if (tableName.endsWith(TABLE_ENDING)) {
            return FileIO.convertToFile(schemaName, tableName);
        }
        return FileIO.convertToFile(schemaName, tableName + TABLE_ENDING);
    }
}

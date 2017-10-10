package io;

import database.Table;

import java.io.File;
import java.io.IOException;

public class TableIO {
    private static final XmlInput xmlInput = new XmlInput();
    private static final XmlOutput xmlOutput = new XmlOutput();

    public static Table readTable(String schemaName, String tableName) throws IOException {
        return xmlInput.readFile(FileIO.convertToFile(schemaName, tableName));
    }

    public static void writeTable(String schemaName, String tableName, Table table) throws IOException {
        xmlOutput.writeFile(FileIO.convertToFile(schemaName, tableName), table);
    }
}

package io;

import database.Table;

import java.io.File;
import java.io.IOException;

public class TableIO {
    private static XmlInput xmlInput = new XmlInput();
    private static XmlOutput xmlOutput = new XmlOutput();

    public static Table readTable(String schemaNmae, String tableName) throws IOException {
        return xmlInput.readFile(new File(tableName));
    }

    public static void writeTable(String schemaName, String tableName, Table table) throws IOException {
        xmlOutput.writeFile(new File(tableName), table);
    }
}

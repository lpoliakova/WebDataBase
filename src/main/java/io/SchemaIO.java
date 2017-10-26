package io;

import database.Schema;
import database.Table;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class SchemaIO {
    public static void createSchema(String name) {
        File schema = new File(name);
        if (schema.exists()) {
            throw new IllegalArgumentException("schema with name " + name + " already exists");
        }
        FileIO.createDirectory(name);
    }

    public static void loadSchema(Schema schema) throws IOException{
        File schemaDir = new File(schema.getName());
        if (!schemaDir.exists()) {
            throw new IllegalArgumentException("schema with name " + schema.getName() + " does not exist");
        }
        if (!schemaDir.isDirectory()) {
            throw new IllegalArgumentException(schema.getName() + " is not a directory");
        }
        for (String tableName : listSchemaTableNames(schemaDir)) {
            Table table = TableIO.readTable(schema.getName(), tableName);
            schema.addTable(table.getName(), table);
        }
    }

    public static void deleteSchema(String name) {
        File schema = new File(name);
        if (!schema.exists()) {
            throw new IllegalArgumentException("schema with name " + name + " does not exist");
        }
        FileIO.deleteDirectory(name);
    }

    private static List<String> listSchemaTableNames(File schema) {
        File[] files = schema.listFiles();
        if (files != null) {
            return Arrays.stream(files).filter(f -> f.toString().endsWith(TableIO.TABLE_ENDING))
                    .map(f -> f.getName())
                    .map(n -> n.substring(0, n.length() - TableIO.TABLE_ENDING.length()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}

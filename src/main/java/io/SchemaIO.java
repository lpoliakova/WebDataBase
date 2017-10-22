package io;

import database.Schema;
import database.Table;

import java.io.File;
import java.io.IOException;

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
        File[] files = schemaDir.listFiles();
        if (files != null) {
            for (File file : files) {
                Table table = TableIO.readTable(schema.getName(), file.getName());
                schema.addTable(table.getName(), table);
            }
        }
    }

    public static void deleteSchema(String name) {
        File schema = new File(name);
        if (!schema.exists()) {
            throw new IllegalArgumentException("schema with name " + name + " does not exist");
        }
        FileIO.deleteDirectory(name);
    }
}

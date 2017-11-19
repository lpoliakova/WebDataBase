package server.io;

import shared.Schema;
import shared.Table;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class SchemaIO {
    final static String DATABASE_LOCATION = "DATABASE";

    public static String[] listSchemaNames() {
        File db = new File(DATABASE_LOCATION);

        if (!db.exists()) {
            FileIO.createDirectory(db);
        }

        File[] files = db.listFiles();
        if (files != null) {
            return Arrays.stream(files)
                    .filter(File::isDirectory)
                    .map(File::getName)
                    .toArray(String[]::new);
        }
        return new String[0];
    }

    public static void createSchema(String name) {
        File schema = FileIO.getSchemaLocation(name);
        if (schema.exists()) {
            throw new IllegalArgumentException("schema with name " + name + " already exists");
        }
        FileIO.createDirectory(schema);
    }

    public static void loadSchema(Schema schema) throws IOException{
        File schemaDir = FileIO.getSchemaLocation(schema.getName());
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
        File schema = FileIO.getSchemaLocation(name);
        if (!schema.exists()) {
            throw new IllegalArgumentException("schema with name " + name + " does not exist");
        }
        FileIO.deleteDirectory(schema);
    }

    private static List<String> listSchemaTableNames(File schema) {
        File[] files = schema.listFiles();
        if (files != null) {
            return Arrays.stream(files)
                    .filter(f -> f.toString().endsWith(TableIO.TABLE_ENDING))
                    .map(File::getName)
                    .map(n -> n.substring(0, n.length() - TableIO.TABLE_ENDING.length()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}

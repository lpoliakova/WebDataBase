package database;

import io.SchemaIO;
import io.TableIO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Schema {
    private String name;
    private Map<String, Table> tables;

    public static Schema createSchema(String name) {
        Schema schema = new Schema(name);
        SchemaIO.createSchema(schema.getName());
        return schema;
    }

    public static Schema loadSchema(String name) {
        Schema schema = new Schema(name);
        try {
            SchemaIO.loadSchema(schema);
        } catch (IOException ex) {
            System.out.println("error during schema loading: " + ex.getMessage());
            ex.printStackTrace();
        }
        return schema;
    }

    public static void deleteSchema(Schema schema) {
        SchemaIO.deleteSchema(schema.name);
    }

    private Schema(String name) {
        this.name = name;
        tables = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Table> getTables() {
        return tables;
    }

    public void addTable(String tableName, Table table) {
        if (tables.get(tableName) != null){
            throw new IllegalArgumentException("table with name " + name + " already exists");
        }
        tables.put(tableName, table);
        try {
            TableIO.writeTable(this.name, tableName, table);
        } catch (IOException ex) {
            System.out.println("error during table write down: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void addTable(String tableName, Set<Attribute> attributes) {
        if (tables.get(tableName) != null){
            throw new IllegalArgumentException("table with name " + name + " already exists");
        }
        Table table = new Table(name, attributes);
        tables.put(tableName, table);
        try {
            TableIO.writeTable(this.name, tableName, table);
        } catch (IOException ex) {
            System.out.println("error during table write down: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void deleteTable(String tableName) {
        if (tables.get(tableName) == null){
            throw new IllegalArgumentException("table with name " + name + "does not exists");
        }
        tables.remove(tableName);
        TableIO.deleteTable(this.name, tableName);
    }

    public Table readTableFromDatabase(String tableName) {
        if (tables.get(tableName) == null){
            throw new IllegalArgumentException("table with name " + name + "does not exists");
        }
        Table table = null;
        try {
           table =  TableIO.readTable(this.name, tableName);
        } catch (IOException ex) {
            System.out.println("error during table reading: " + ex.getMessage());
            ex.printStackTrace();
        }
        return table;
    }

    public void writeTableToDatabase(String tableName) {
        if (tables.get(tableName) == null){
            throw new IllegalArgumentException("table with name " + name + "does not exists");
        }
        try {
            TableIO.writeTable(this.name, tableName, tables.get(tableName));
        } catch (IOException ex) {
            System.out.println("error during table write down: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
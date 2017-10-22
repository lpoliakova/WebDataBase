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

    public Schema(String name) {
        this.name = name;
        tables = new HashMap<>();
        FileIO.createDirectory(name);
    }

    public String getName() {
        return name;
    }

    public Map<String, Table> getTables() {
        return tables;
    }

    public void addTable(String tableName, Table table){
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

    public void addTable(String tableName, Set<Attribute> attributes){
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

    public void deleteTable(String tableName){
        if (tables.get(tableName) == null){
            throw new IllegalArgumentException("table with name " + name + "does not exists");
        }
        FileIO.deleteFile(this.name, tableName);
    }

    public Table readTableFromDatabase(String tableName){
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

    public void writeTableToDatabase(String tableName){
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
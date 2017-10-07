package database;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Schema {
    private String name;
    private Map<String, Table> tables;

    public Schema(String name) {
        this.name = name;
        tables = new HashMap<>();
        //TODO: write into database
    }

    public String getName() {
        return name;
    }

    public Map<String, Table> getTables() {
        return tables;
    }

    public void addTable(String name, Table table){
        if (tables.get(name) != null){
            throw new IllegalArgumentException("table with name " + name + " already exists");
        }
        tables.put(name, table);
        //TODO: write into database
    }

    public void addTable(String name, Set<Attribute> attributes){
        if (tables.get(name) != null){
            throw new IllegalArgumentException("table with name " + name + " already exists");
        }
        tables.put(name, new Table(name, attributes));
        //TODO: write into database
    }

    public void deleteTable(String name){
        if (tables.get(name) == null){
            throw new IllegalArgumentException("table with name " + name + "does not exists");
        }
        //TODO: delete from database
    }

    public Table readTableFromDatabase(String name){
        if (tables.get(name) == null){
            throw new IllegalArgumentException("table with name " + name + "does not exists");
        }
        //TODO: get from database
        return null;
    }

    public void writeTableToDatabase(String name){
        if (tables.get(name) == null){
            throw new IllegalArgumentException("table with name " + name + "does not exists");
        }
        //TODO: put into database
    }
}
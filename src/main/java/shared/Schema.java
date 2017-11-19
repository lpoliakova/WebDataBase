package shared;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@XmlRootElement
public class Schema implements Serializable{
    @XmlElement
    private String name;
    @XmlElement
    private Map<String, Table> tables;

    private Schema() {}

    public Schema(String name) {
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
    }

    public void addTable(String tableName, Set<Attribute> attributes) {
        if (tables.get(tableName) != null){
            throw new IllegalArgumentException("table with name " + name + " already exists");
        }
        Table table = new Table(name, attributes);
        tables.put(tableName, table);
    }

    public Table getTable(String tableName) {
        Table table = tables.get(tableName);
        if (table == null){
            throw new IllegalArgumentException("table with name " + name + " does not exist");
        }
        return table;
    }

    public void deleteTable(String tableName) {
        if (tables.get(tableName) == null){
            throw new IllegalArgumentException("table with name " + name + " does not exist");
        }
        tables.remove(tableName);
    }

    @Override
    public String toString() {
        return "Schema name = '" + name + "'" + System.lineSeparator() +
                "Number of tables = " + tables.size() + System.lineSeparator();
    }
}
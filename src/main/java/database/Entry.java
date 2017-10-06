package database;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Entry {
    private Map<String, Attribute> values = new HashMap<>();

    public Entry(Map<String, Attribute> values) {
        this.values = values;
    }

    public Collection<Attribute> getValues() {
        return values.<Attribute>values();
    }

    public Attribute getValueByName(String name){
        return values.get(name);
    }

    public void setValueByName(String name, DatabaseTypes type, String newValue){
        Attribute oldValue = values.get(name);
        if (oldValue == null) {
            throw new IllegalArgumentException("Attribute with name " + name + " does not exist in this entry");
        }
        if (oldValue.getType() != type) {
            throw new IllegalArgumentException("Attribute with name " + name + " has another type");
        }
        oldValue.setValue(newValue);
    }

    public List<DatabaseTypes> getTypes(){
        return values.<Attribute>values().stream().map(Attribute::getType).collect(Collectors.toList());
    }
}

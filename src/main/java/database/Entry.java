package database;

import java.util.*;
import java.util.stream.Collectors;

public class Entry {
    private Map<Attribute, String> values = new HashMap<>();

    public Entry(Map<Attribute, String> values) {
        this.values = values;
    }

    public Set<Attribute> getAttributes() {
        return values.<Attribute>keySet();
    }

    public Collection<String> getValues() {
        return values.<String>values();
    }

    public String getValueByAttribute(Attribute attribute){
        return values.get(attribute);
    }

    public void setValueByAttribute(Attribute attribute, String newValue){
        String oldValue = values.get(attribute);
        if (oldValue == null) {
            throw new IllegalArgumentException(attribute + " does not exist in this entry");
        }
        values.put(attribute, newValue);
    }
}

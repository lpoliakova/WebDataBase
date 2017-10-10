package database;

import java.util.*;

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

    public Map<Attribute, String> getEntry() {
        return values;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        return values != null ? values.equals(entry.values) : entry.values == null;
    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }
}

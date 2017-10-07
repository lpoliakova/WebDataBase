package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Table {
    private final String name;
    private final Set<Attribute> attributes;
    private List<Entry> entries;

    public Table(String name, Set<Attribute> attributes) {
        this.name = name;
        this.attributes = attributes;
        this.entries = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void addEntry(Entry newEntry) {
        if (checkAttributes(newEntry.getAttributes())) {
            throw new IllegalArgumentException("new entry and table have incompatible types");
        }
        entries.add(newEntry);
    }

    private boolean checkAttributes(Set<Attribute> entryAttributes){
        return attributes.containsAll(entryAttributes) && entryAttributes.containsAll(attributes);
    }
}

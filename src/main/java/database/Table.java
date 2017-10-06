package database;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private final String name;
    private final List<DatabaseTypes> types;
    private List<Entry> entries;

    public Table(String name, List<DatabaseTypes> types) {
        this.name = name;
        this.types = types;
        this.entries = new ArrayList<>(E);
    }

    public String getName() {
        return name;
    }

    public List<DatabaseTypes> getTypes() {
        return types;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void addEntry(Entry entry) {

    }

    private boolean checkAttributes(List<DatabaseTypes> entryTypes){
        return types.containsAll(entryTypes) && entryTypes.containsAll(types);
    }
}

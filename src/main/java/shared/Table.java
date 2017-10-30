package shared.database;

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
        // TODO: fix to unmodifiable collection
    }

    public void addEntry(Entry newEntry) {
        if (!checkAttributes(newEntry.getAttributes())) {
            throw new IllegalArgumentException("new entry and table have incompatible types");
        }
        entries.add(newEntry);
    }

    public void addAllEntries(List<Entry> newEntries) {
        for (Entry entry : newEntries) {
            addEntry(entry);
        }
    }

    public void deleteEntry(Entry entry) {
        if (!checkAttributes(entry.getAttributes())) {
            throw new IllegalArgumentException("entry to delete and table have incompatible types");
        }
        entries.remove(entry);
    }

    public static Table subtractTables(String newTableName, Table firstTable, Table secondTable) {
        if (!firstTable.checkAttributes(secondTable.getAttributes())) {
            throw new IllegalArgumentException("tables have incompatible types");
        }
        Table table = new Table(newTableName, firstTable.attributes);
        table.addAllEntries(firstTable.getEntries());
        table.getEntries().removeAll(secondTable.getEntries());
        return table;
    }

    public static Table intersectTables(String newTableName, Table firstTable, Table secondTable) {
        if (!firstTable.checkAttributes(secondTable.getAttributes())) {
            throw new IllegalArgumentException("tables have incompatible types");
        }
        Table table = new Table(newTableName, firstTable.attributes);
        table.addAllEntries(firstTable.getEntries());
        table.getEntries().retainAll(secondTable.getEntries());
        return table;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;

        if (name != null ? !name.equals(table.name) : table.name != null) return false;
        if (attributes != null ? !attributes.equals(table.attributes) : table.attributes != null) return false;
        return entries != null ? entries.equals(table.entries) : table.entries == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (entries != null ? entries.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Table name = '" ).append(name).append("'").append(System.lineSeparator());
        for (Attribute attribute : attributes) {
            builder.append(attribute.toString()).append(System.lineSeparator());
        }
        builder.append("Number of entries = ").append(entries.size());
        return builder.toString();
    }

    private boolean checkAttributes(Set<Attribute> entryAttributes) {
        return attributes.size() == entryAttributes.size() && attributes.containsAll(entryAttributes);
    }
}

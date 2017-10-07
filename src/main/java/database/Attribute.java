package database;

public class Attribute {
    private final String name;
    private final DatabaseTypes type;

    public Attribute(String name, DatabaseTypes type, String value) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public DatabaseTypes getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attribute attribute = (Attribute) o;

        if (name != null ? !name.equals(attribute.name) : attribute.name != null) return false;
        return type == attribute.type;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}

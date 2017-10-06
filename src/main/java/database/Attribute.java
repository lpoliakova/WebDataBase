package database;

public class Attribute {
    private final String name;
    private final DatabaseTypes type;
    private String value;

    public Attribute(String name, DatabaseTypes type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public DatabaseTypes getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

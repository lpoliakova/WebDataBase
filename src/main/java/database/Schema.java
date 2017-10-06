package database;

import java.util.HashMap;
import java.util.Map;

public class Schema {
    private String name;
    private Map<String, Table> tables = new HashMap<>();
}
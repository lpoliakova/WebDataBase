package client.utils;

import database.Schema;
import database.Table;

public class WorkingSet {

    private static Schema currentSchema;
    private static Table currentTable;

    public synchronized static Schema getCurrentSchema() {
        return currentSchema;
    }

    public synchronized static void setCurrentSchema(Schema currentSchema) {
        WorkingSet.currentSchema = currentSchema;
    }

    public synchronized static Table getCurrentTable() {
        return currentTable;
    }

    public synchronized static void setCurrentTable(Table currentTable) {
        WorkingSet.currentTable = currentTable;
    }
}

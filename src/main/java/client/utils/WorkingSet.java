package client.utils;

import shared.Schema;
import shared.Table;

public class WorkingSet {

    private static Schema currentSchema;
    private static Table currentTable;
    private static ServerConnection connection;

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

    public static ServerConnection getConnection() {
        return connection;
    }

    public static void setConnection(ServerConnection connection) {
        WorkingSet.connection = connection;
    }
}

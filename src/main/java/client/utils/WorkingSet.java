package client.utils;

import shared.database.Schema;
import shared.database.Table;
import server.api.DatabaseInterface;

public class WorkingSet {

    private static Schema currentSchema;
    private static Table currentTable;
    private static DatabaseInterface dbServer;

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

    public synchronized static DatabaseInterface getDbServer() {
        return dbServer;
    }

    public synchronized static void setDbServer(DatabaseInterface dbServer) {
        WorkingSet.dbServer = dbServer;
    }
}
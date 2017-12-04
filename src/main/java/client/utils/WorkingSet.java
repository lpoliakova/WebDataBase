package client.utils;

import shared.Schema;
import shared.Table;

public class WorkingSet {

    private static Schema currentSchema;
    private static Table currentTable;
    private static Table currentOtherTable;

    private static boolean oneTable = true;
    private static boolean firstTable = true;

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

    public static Table getCurrentOtherTable() {
        return currentOtherTable;
    }

    public static void setCurrentOtherTable(Table currentOtherTable) {
        WorkingSet.currentOtherTable = currentOtherTable;
    }

    public static boolean isOneTable() {
        return oneTable;
    }

    public static void setOneTable(boolean oneTable) {
        WorkingSet.oneTable = oneTable;
    }

    public static boolean isFirstTable() {
        return firstTable;
    }

    public static void setFirstTable(boolean firstTable) {
        WorkingSet.firstTable = firstTable;
    }

    public static ServerConnection getConnection() {
        return connection;
    }

    public static void setConnection(ServerConnection connection) {
        WorkingSet.connection = connection;
    }
}

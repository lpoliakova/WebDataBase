package client.utils;

import server.api.DatabaseInterface;
import shared.Schema;
import shared.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerConnection {
    private DatabaseInterface dbServer;

    public ServerConnection(DatabaseInterface databaseInterface) {
        dbServer = databaseInterface;
    }

    // TODO: do not catch exceptions

    public String[] listSchemaNames() {
        String[] schemaNames = new String[0];
        try {
            schemaNames = dbServer.listSchemaNames();
        } catch (IOException ex) {
            System.out.println("error during loading schema names: " + ex.getMessage());
            ex.printStackTrace();
        }
        return schemaNames;
    }

    public void createSchema(String name) {
        try {
            dbServer.createSchema(name);
        } catch (IOException ex) {
            System.out.println("error during schema create: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Schema loadSchema(String name) {
        Schema schema = new Schema(name);
        try {
            schema = dbServer.loadSchema(name);
        } catch (IOException ex) {
            System.out.println("error during schema loading: " + ex.getMessage());
            ex.printStackTrace();
        }
        return schema;
    }

    public void deleteSchema(String schemaName) {
        try {
            dbServer.deleteSchema(schemaName);
        } catch (IOException ex) {
            System.out.println("error during schema deletion: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Table readTable(String schemaName, String tableName) {
        Table table = null;
        try {
            table =  dbServer.readTable(schemaName, tableName);
        } catch (IOException ex) {
            System.out.println("error during table reading: " + ex.getMessage());
            ex.printStackTrace();
        }
        return table;
    }

    public void writeTable(String schemaName, Table table) {
        try {
            dbServer.writeTable(schemaName, table);
        } catch (IOException ex) {
            System.out.println("error during table write down: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void deleteTable(String schemaName, String tableName) {
        try {
            dbServer.deleteTable(schemaName, tableName);
        } catch (IOException ex) {
            System.out.println("error during table deletion: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

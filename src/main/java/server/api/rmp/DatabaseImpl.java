package server.api.rmp;

import server.api.DatabaseInterface;
import server.io.SchemaIO;
import server.io.TableIO;
import shared.Schema;
import shared.Table;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


public class DatabaseImpl extends UnicastRemoteObject implements DatabaseInterface {
    //TODO: exception handling
    public DatabaseImpl() throws RemoteException {
        super();
    }

    @Override
    public List<String> listSchemaNames() throws RemoteException {
        System.out.println("listing schemas");
        return SchemaIO.listSchemaNames();
    }

    @Override
    public void createSchema(String name) throws RemoteException {
        System.out.println("creating schema");
        SchemaIO.createSchema(name);
    }

    @Override
    public Schema loadSchema(String name) throws IOException {
        System.out.println("loading schema");
        Schema schema = new Schema(name);
        SchemaIO.loadSchema(schema);
        return schema;
    }

    @Override
    public void deleteSchema(String name) throws RemoteException {
        System.out.println("deleting schema");
        SchemaIO.deleteSchema(name);
    }

    @Override
    public Table readTable(String schemaName, String tableName) throws IOException {
        System.out.println("reading table");
        return TableIO.readTable(schemaName, tableName);
    }

    @Override
    public void writeTable(String schemaName, Table table) throws IOException {
        System.out.println("writing table");
        TableIO.writeTable(schemaName, table.getName(), table);
    }

    @Override
    public void deleteTable(String schemaName, String tableName) throws RemoteException {
        System.out.println("deleting table");
        TableIO.deleteTable(schemaName, tableName);
    }
}

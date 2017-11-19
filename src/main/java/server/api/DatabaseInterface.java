package server.api;

import shared.Schema;
import shared.Table;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DatabaseInterface extends Remote {
    String[] listSchemaNames() throws RemoteException;

    void createSchema(String name) throws RemoteException;

    Schema loadSchema(String name) throws IOException;

    void deleteSchema(String name) throws RemoteException;

    Table readTable(String schemaName, String tableName) throws IOException;

    void writeTable(String schemaName, Table table) throws IOException;

    void deleteTable(String schemaName, String tableName) throws RemoteException;

}

package server.api.rmp;

import server.api.DatabaseInterface;
import server.io.SchemaIO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by oradchykova on 10/31/17.
 */
public class DatabaseImpl extends UnicastRemoteObject implements DatabaseInterface {
    public DatabaseImpl() throws RemoteException {
        super();
    }

    @Override
    public List<String> listSchemaNames() throws RemoteException {
        return SchemaIO.listSchemaNames();
    }
}

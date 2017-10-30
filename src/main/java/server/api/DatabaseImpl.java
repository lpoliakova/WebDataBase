package server.api;

import server.io.SchemaIO;

import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;
import java.util.List;

public class DatabaseImpl extends PortableRemoteObject implements DatabaseInterface{

    public DatabaseImpl() throws RemoteException {
        super();
    }

    @Override
    public List<String> listSchemaNames() throws RemoteException {
        return SchemaIO.listSchemaNames();
    }
}

package server.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DatabaseInterface extends Remote {
    List<String> listSchemaNames() throws RemoteException;
}

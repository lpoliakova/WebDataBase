package client;

import client.utils.WorkingSet;
import client.view.StartFrame;
import server.api.DatabaseInterface;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.awt.*;

public class EntryPoint {

    public static void main(String[] args) {
        try {
            createClientConnection();
            EventQueue.invokeLater(StartFrame::new);

        } catch (NamingException ex) {
            System.out.println("Connection error " + ex);
            ex.printStackTrace();
            return;
        }
    }

    private static void createClientConnection() throws NamingException{
        Context ic;
        Object objref;
        DatabaseInterface dbServer;

        ic = new InitialContext();

        // STEP 1: Get the Object reference from the Name Service
        // using JNDI call.
        objref = ic.lookup("DatabaseService");
        System.out.println("Client: Obtained a ref. to Database server.");

        // STEP 2: Narrow the object reference to the concrete type and
        // invoke the method.
        dbServer = (DatabaseInterface) PortableRemoteObject.narrow(
                objref, DatabaseInterface.class);

        WorkingSet.setDbServer(dbServer);

    }
}

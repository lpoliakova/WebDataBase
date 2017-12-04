package client;

import client.utils.CommonComponents;
import client.utils.ServerConnection;
import client.utils.WorkingSet;
import client.view.StartFrame;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import server.api.DatabaseInterface;
import server.api.corba.DatabaseImplBridge;
import server.api.corba.generated.DatabaseInterfaceHelper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.awt.*;
import java.net.URL;
import java.rmi.Naming;

public class EntryPoint {

    public static void main(String[] args) {
        try {
            //createClientRmpConnection();
            //createClientIiopConnection();
            createClientSOAPConnection();
            //createClientCORBAConnection();
            //createClientIiopToCorbaConnection();
            EventQueue.invokeLater(StartFrame::new);
        } catch (Exception ex) {
            CommonComponents.showConnectionException(ex);
        }

    }

    private static void createClientIiopConnection() throws NamingException{
        Context ic = new InitialContext();

        // STEP 1: Get the Object reference from the Name Service
        // using JNDI call.
        Object objref = ic.lookup("DatabaseService");
        System.out.println("Client: Obtained a ref. to Database server.");

        // STEP 2: Narrow the object reference to the concrete type and
        // invoke the method.
        DatabaseInterface dbServer = (DatabaseInterface) PortableRemoteObject.narrow(
                objref, DatabaseInterface.class);

        WorkingSet.setConnection(new ServerConnection(dbServer));

    }

    private static void createClientRmpConnection() throws Exception{
        DatabaseInterface dbServer = (DatabaseInterface) Naming.lookup("rmi://localhost:1099/DatabaseService");
        WorkingSet.setConnection(new ServerConnection(dbServer));
    }

    private static void createClientSOAPConnection() throws Exception{
        URL url = new URL("http://localhost:8888/ws_database");
        QName qname = new QName("http://soap.api.server/", "DatabaseServiceService");
        Service service = Service.create(url, qname);
        server.api.soap.DatabaseInterface dbService = service.getPort(server.api.soap.DatabaseInterface.class);
        WorkingSet.setConnection(new ServerConnection(dbService));
    }

    private static void createClientCORBAConnection() throws Exception {
        try{
            // create and initialize the ORB
            String[] parameters = {"-ORBInitialPort", "1050", "-ORBInitialHost", "localhost"};
            ORB orb = ORB.init(parameters, null);

            // get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            String name = "Database";
            server.api.corba.generated.DatabaseInterface dbService = DatabaseInterfaceHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Obtained a handle on server object: " + dbService);

            WorkingSet.setConnection(new ServerConnection(new DatabaseImplBridge(dbService)));
        } catch (Exception e) {
            System.out.println("ERROR : " + e) ;
            e.printStackTrace(System.out);
        }
    }

    private static void createClientIiopToCorbaConnection() throws NamingException{
        Context ic = new InitialContext();

        // STEP 1: Get the Object reference from the Name Service
        // using JNDI call.
        Object objref = ic.lookup("Database");
        System.out.println("Client: Obtained a ref. to Database server.");

        // STEP 2: Narrow the object reference to the concrete type and
        // invoke the method.
        server.api.corba.generated.DatabaseInterface dbServer = (server.api.corba.generated.DatabaseInterface) PortableRemoteObject.narrow(
                objref, server.api.corba.generated.DatabaseInterface.class);

        WorkingSet.setConnection(new ServerConnection(new DatabaseImplBridge(dbServer)));

    }

}

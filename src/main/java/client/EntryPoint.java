package client;

import client.utils.CommonComponents;
import client.utils.ServerConnection;
import client.utils.WorkingSet;
import client.view.StartFrame;
import server.api.DatabaseInterface;
import server.api.soap.DatabaseService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.util.Hashtable;

public class EntryPoint {

    public static void main(String[] args) {
        try {
            //createClientRmpConnection();
            //createClientIiopConnection();
            createClientSOAPConnection();
            //EventQueue.invokeLater(StartFrame::new);
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

    private static void createClientSOAPConnection() throws MalformedURLException{
        URL url = new URL("http://localhost:8888/ws_database");
        QName qname = new QName("http://soap.api.server/", "DatabaseServiceService");
        Service service = Service.create(url, qname);
        server.api.soap.DatabaseInterface dbService = service.getPort(server.api.soap.DatabaseInterface.class);
        dbService.createSchema("subjects");
        dbService.deleteSchema("subjects");
    }
}

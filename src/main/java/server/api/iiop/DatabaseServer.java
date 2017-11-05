package server.api.iiop;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Hashtable;

public class DatabaseServer {
    public static void main(String[] args) {
        try {
            DatabaseImpl dbRef = new DatabaseImpl();

            Context initialNamingContext = new InitialContext();
            initialNamingContext.rebind("DatabaseService", dbRef );

            System.out.println("Database Server: Ready...");

        } catch (Exception ex) {
            System.out.println("Connection error: " + ex);
            ex.printStackTrace();
        }
    }
}

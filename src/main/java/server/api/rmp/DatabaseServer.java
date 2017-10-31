package server.api.rmp;

import server.api.DatabaseInterface;

import java.rmi.Naming;

/**
 * Created by oradchykova on 10/31/17.
 */
public class DatabaseServer {
    public DatabaseServer() {
        try {
            DatabaseInterface dbRef = new DatabaseImpl();
            Naming.rebind("rmi://localhost:1099/DatabaseService", dbRef);

            System.out.println("Database Server: Ready...");

        } catch (Exception ex) {
            System.out.println("Connection trouble: " + ex);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DatabaseServer();
    }
}

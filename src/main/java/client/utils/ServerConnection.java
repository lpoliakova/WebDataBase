package client.utils;

import server.api.DatabaseInterface;

/**
 * Created by oradchykova on 10/31/17.
 */
public class ServerConnection {

    private static DatabaseInterface dbServer;

    public synchronized static DatabaseInterface getDbServer() {
        return dbServer;
    }

    public synchronized static void setDbServer(DatabaseInterface dbServer) {
        ServerConnection.dbServer = dbServer;
    }
}

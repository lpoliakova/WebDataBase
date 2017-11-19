package server.api.soap;

import javax.xml.ws.Endpoint;

public class DatabaseServer {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/ws_database", new DatabaseService());

        System.out.println("Database Server: Ready...");
    }
}

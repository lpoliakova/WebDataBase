package server.api.soap;

import server.io.SchemaIO;
import server.io.TableIO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(endpointInterface = "server.api.soap.DatabaseInterface")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class DatabaseService implements DatabaseInterface {

    /*@WebMethod
    public List<String> listSchemaNames() {
        System.out.println("listing schemas");
        return SchemaIO.listSchemaNames();
    }*/

    @WebMethod
    public void createSchema(String name) {
        System.out.println("creating schema");
        SchemaIO.createSchema(name);
    }

    @WebMethod
    public void deleteSchema(String name) {
        System.out.println("deleting schema");
        SchemaIO.deleteSchema(name);
    }

    @WebMethod
    public void deleteTable(String schemaName, String tableName) {
        System.out.println("deleting table");
        TableIO.deleteTable(schemaName, tableName);
    }
}

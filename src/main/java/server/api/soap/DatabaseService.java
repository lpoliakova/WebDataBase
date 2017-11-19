package server.api.soap;

import server.io.SchemaIO;
import server.io.TableIO;
import shared.Schema;
import shared.Table;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.IOException;

@WebService(endpointInterface = "server.api.soap.DatabaseInterface")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class DatabaseService implements DatabaseInterface {

    @Override
    @WebMethod
    public String[] listSchemaNames() {
        System.out.println("listing schemas");
        return SchemaIO.listSchemaNames();
    }

    @Override
    @WebMethod
    public void createSchema(String name) {
        System.out.println("creating schema");
        SchemaIO.createSchema(name);
    }

    @Override
    @WebMethod
    public Schema loadSchema(String name) throws IOException {
        System.out.println("loading schema");
        Schema schema = new Schema(name);
        SchemaIO.loadSchema(schema);
        return schema;
    }

    @Override
    @WebMethod
    public void deleteSchema(String name) {
        System.out.println("deleting schema");
        SchemaIO.deleteSchema(name);
    }

    @Override
    @WebMethod
    public Table readTable(String schemaName, String tableName) throws IOException {
        System.out.println("reading table");
        return TableIO.readTable(schemaName, tableName);
    }

    @Override
    @WebMethod
    public void writeTable(String schemaName, @WebParam(name = "table") Table table) throws IOException {
        System.out.println("writing table");
        TableIO.writeTable(schemaName, table.getName(), table);
    }

    @Override
    @WebMethod
    public void deleteTable(String schemaName, String tableName) {
        System.out.println("deleting table");
        TableIO.deleteTable(schemaName, tableName);
    }
}

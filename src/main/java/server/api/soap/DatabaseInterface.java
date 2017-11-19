package server.api.soap;

import shared.Schema;
import shared.Table;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.IOException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DatabaseInterface extends server.api.DatabaseInterface{

    @WebMethod
    String[] listSchemaNames();

    @WebMethod
    void createSchema(String name);

    @WebMethod
    Schema loadSchema(String name) throws IOException;

    @WebMethod
    void deleteSchema(String name);

    @WebMethod
    Table readTable(String schemaName, String tableName) throws IOException;

    @WebMethod
    void writeTable(String schemaName, Table table) throws IOException;

    @WebMethod
    void deleteTable(String schemaName, String tableName);
}

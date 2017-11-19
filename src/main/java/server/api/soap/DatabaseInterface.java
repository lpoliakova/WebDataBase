package server.api.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DatabaseInterface {

    @WebMethod
    void createSchema(String name);

    @WebMethod
    void deleteSchema(String name);

    @WebMethod
    void deleteTable(String schemaName, String tableName);
}

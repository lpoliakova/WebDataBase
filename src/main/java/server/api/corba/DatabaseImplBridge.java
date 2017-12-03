package server.api.corba;

import server.api.corba.generated.DatabaseInterface;
import shared.Schema;
import shared.Table;

public class DatabaseImplBridge implements server.api.DatabaseInterface {
    private final DatabaseInterface implementation;

    public DatabaseImplBridge(DatabaseInterface implementation) {
        this.implementation = implementation;
    }

    @Override
    public String[] listSchemaNames() {
        return implementation.listSchemaNames();
    }

    @Override
    public void createSchema(String name) {
        implementation.createSchema(name);
    }

    @Override
    public Schema loadSchema(String name) {
        return Transfer.toSchema(implementation.loadSchema(name));
    }

    @Override
    public void deleteSchema(String name) {
        implementation.deleteSchema(name);
    }

    @Override
    public Table readTable(String schemaName, String tableName) {
        return Transfer.toTable(implementation.readTable(schemaName, tableName));
    }

    @Override
    public void writeTable(String schemaName, Table table) {
        implementation.writeTable(schemaName, Transfer.fromTable(table));
    }

    @Override
    public void deleteTable(String schemaName, String tableName) {
        implementation.deleteTable(schemaName, tableName);
    }
}

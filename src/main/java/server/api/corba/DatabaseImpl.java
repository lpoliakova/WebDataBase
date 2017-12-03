package server.api.corba;

import server.api.corba.generated.DatabaseInterfacePOA;
import server.api.corba.generated.TransferSchema;
import server.api.corba.generated.TransferTable;
import server.io.SchemaIO;
import server.io.TableIO;
import shared.Schema;
import shared.Table;

import java.io.IOException;
import java.util.HashSet;

public class DatabaseImpl extends DatabaseInterfacePOA {
    @Override
    public String[] listSchemaNames() {
        System.out.println("listing schemas");
        return SchemaIO.listSchemaNames();
    }

    @Override
    public void createSchema(String name) {
        System.out.println("creating schema");
        SchemaIO.createSchema(name);
    }

    @Override
    public TransferSchema loadSchema(String name) {
        System.out.println("loading schema");
        Schema schema = new Schema(name);
        try {
            SchemaIO.loadSchema(schema);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return Transfer.fromSchema(schema);
    }

    @Override
    public void deleteSchema(String name) {
        System.out.println("deleting schema");
        SchemaIO.deleteSchema(name);
    }

    @Override
    public void writeTable(String schemaName, TransferTable table) {
        System.out.println("writing table");
        Table realTable = Transfer.toTable(table);
        try {
            TableIO.writeTable(schemaName, realTable.getName(), realTable);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public TransferTable readTable(String schemaName, String tableName) {
        System.out.println("reading table");
        Table table = new Table("", new HashSet<>());
        try {
            table = TableIO.readTable(schemaName, tableName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return Transfer.fromTable(table);
    }

    @Override
    public void deleteTable(String schemaName, String tableName) {
        System.out.println("deleting table");
        TableIO.deleteTable(schemaName, tableName);
    }
}

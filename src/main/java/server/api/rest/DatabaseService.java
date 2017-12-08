package server.api.rest;

import server.io.SchemaIO;
import server.io.TableIO;
import shared.Schema;
import shared.Table;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class DatabaseService {

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response listSchemaNames() {
        System.out.println("listing schemas");
        return Response.ok(new SchemaList(SchemaIO.listSchemaNames())).build();
    }

    @PUT
    @Path("database")
    public Response createSchema(@QueryParam("schema") String name) {
        System.out.println("creating schema");
        SchemaIO.createSchema(name);
        return Response.ok().build();
    }

    @GET
    @Path("database")
    @Produces(MediaType.APPLICATION_XML)
    public Response loadSchema(@QueryParam("schema") String name) {
        try {
            System.out.println("loading schema");
            Schema schema = new Schema(name);
            SchemaIO.loadSchema(schema);
            return Response.ok(schema).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("database")
    public Response deleteSchema(@QueryParam("schema") String name) {
        System.out.println("deleting schema");
        SchemaIO.deleteSchema(name);
        return Response.ok().build();
    }

    @GET
    @Path("database/{schema}")
    @Produces(MediaType.APPLICATION_XML)
    public Response readTable(@PathParam("schema") String schemaName, @QueryParam("table") String tableName) {
        try {
            System.out.println("reading table");
            return Response.ok(TableIO.readTable(schemaName, tableName)).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("database/{schema}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response writeTable(@PathParam("schema") String schemaName, Table table) {
        try {
            System.out.println("writing table");
            TableIO.writeTable(schemaName, table.getName(), table);
            return Response.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("database/{schema}")
    public Response deleteTable(@PathParam("schema") String schemaName, @QueryParam("table") String tableName) {
        System.out.println("deleting table");
        TableIO.deleteTable(schemaName, tableName);
        return Response.ok().build();
    }
}

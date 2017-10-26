package databaseTests;

import database.Schema;
import database.Table;
import org.junit.Assert;
import org.junit.Test;
import utils.TableTestExamples;

/**
 * Created by oradchykova on 10/25/17.
 */
public class SchemaTests {
    @Test
    public void schemaWorkflowTest() {
        String schemaName = "peopleDB";
        Schema schema = Schema.createSchema(schemaName);
        Table table = TableTestExamples.createArturMikeTable();
        schema.addTable(table.getName(), table);

        Table actualTable = schema.readTableFromDatabase(table.getName());
        Assert.assertEquals(table, actualTable);

        Schema actualSchema = Schema.loadSchema(schema.getName());
        Assert.assertEquals(table, actualSchema.getTables().get(table.getName()));

        schema.deleteTable(table.getName());
        Assert.assertNull(schema.getTables().get(table.getName()));

        Schema.deleteSchema(schema);
    }
}

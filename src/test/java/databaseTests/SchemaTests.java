package databaseTests;

import server.io.SchemaIO;
import server.io.TableIO;
import shared.Schema;
import shared.Table;
import org.junit.Assert;
import org.junit.Test;
import utils.TableTestExamples;

/**
 * Created by oradchykova on 10/25/17.
 */
public class SchemaTests {
    @Test
    public void schemaWorkflowTest() {
        try {
            String schemaName = "peopleDB";
            Schema schema = new Schema(schemaName);
            SchemaIO.createSchema(schemaName);
            Table table = TableTestExamples.createArturMikeTable();
            schema.addTable(table.getName(), table);
            TableIO.writeTable(schemaName, table.getName(), table);

            Schema actualSchema = new Schema(schemaName);
            SchemaIO.loadSchema(actualSchema);
            Assert.assertEquals(table, actualSchema.getTables().get(table.getName()));

            schema.deleteTable(table.getName());
            Assert.assertNull(schema.getTables().get(table.getName()));

            SchemaIO.deleteSchema(schema.getName());
        } catch (Exception ex) {
            Assert.assertFalse(true);
        }
    }
}

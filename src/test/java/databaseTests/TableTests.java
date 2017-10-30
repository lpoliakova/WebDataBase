package databaseTests;

import shared.database.Table;
import org.junit.Assert;
import org.junit.Test;
import utils.TableTestExamples;

/**
 * Created by oradchykova on 10/25/17.
 */
public class TableTests {

    @Test
    public void subtractTablesTest() {
        Table mikeJim = TableTestExamples.createMikeJimTable();
        Table arturMike = TableTestExamples.createArturMikeTable();

        Table expected = TableTestExamples.createJimTable();

        Table actual = Table.subtractTables(expected.getName(), mikeJim, arturMike);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void intersectTablesTest() {
        Table mikeJim = TableTestExamples.createMikeJimTable();
        Table arturMike = TableTestExamples.createArturMikeTable();

        Table expected = TableTestExamples.createMikeTable();

        Table actual = Table.intersectTables(expected.getName(), mikeJim, arturMike);

        Assert.assertEquals(expected, actual);
    }
}

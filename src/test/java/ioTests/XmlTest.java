package ioTests;

import shared.Schema;
import shared.Table;
import org.junit.Assert;
import org.junit.Test;
import utils.TableTestExamples;

public class XmlTest {
    @Test
    void writeReadTableTest() {
        Schema company = Schema.createSchema("Company");
        Table people = TableTestExamples.createMikeJimTable();
        company.addTable(people.getName(), people);

        Table peopleFromDb = company.readTableFromDatabase(people.getName());
        Assert.assertEquals(people, peopleFromDb);
    }
}

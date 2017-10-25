package ioTests;

import database.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.TableTestExamples;

import java.util.*;

public class XmlTest {
    @Test
    void writeReadTableTest() {
        Schema company = Schema.createSchema("Company");
        Table people = TableTestExamples.createMikeJimTable();
        company.addTable(people.getName(), people);

        Table peopleFromDb = company.readTableFromDatabase(people.getName());
        Assertions.assertEquals(people, peopleFromDb);
    }
}

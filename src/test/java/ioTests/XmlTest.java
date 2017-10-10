package ioTests;

import database.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class XmlTest {
    @Test
    void writeReadTableTest() {
        Schema company = new Schema("Company");
        Table people = createTestTable();
        company.addTable(people.getName(), people);

        Table peopleFromDb = company.readTableFromDatabase(people.getName());
        Assertions.assertEquals(people, peopleFromDb);
    }

    private Table createTestTable() {
        Set<Attribute> attributes = new HashSet<>();
        Attribute name = new Attribute("name", DatabaseTypes.CHAR);
        Attribute age = new Attribute("age", DatabaseTypes.INT);
        attributes.add(name);
        attributes.add(age);
        Table people = new Table("People", attributes);
        Map<Attribute, String> mikeAttributes = new HashMap<>();
        mikeAttributes.put(name, "Mike");
        mikeAttributes.put(age, "21");
        people.addEntry(new Entry(mikeAttributes));
        Map<Attribute, String> jimAttributes = new HashMap<>();
        jimAttributes.put(name, "Jim");
        jimAttributes.put(age, "35");
        people.addEntry(new Entry(jimAttributes));
        return people;
    }
}

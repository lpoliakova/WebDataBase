package utils;

import shared.Attribute;
import shared.DatabaseTypes;
import shared.Entry;
import shared.Table;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by oradchykova on 10/25/17.
 */
public class TableTestExamples {
    public static Table createJimTable() {
        Table people = new Table("Jim", createPeopleAttributes());
        addPerson(people, "Jim", 35);
        return people;
    }

    public static Table createMikeTable() {
        Table people = new Table("Mike", createPeopleAttributes());
        addPerson(people, "Mike", 21);
        return people;
    }

    public static Table createMikeJimTable() {
        Table people = new Table("MikeJim", createPeopleAttributes());
        addPerson(people, "Mike", 21);
        addPerson(people, "Jim", 35);
        return people;
    }

    public static Table createArturMikeTable() {
        Table people = new Table("ArturMike", createPeopleAttributes());
        addPerson(people, "Artur", 15);
        addPerson(people, "Mike", 21);
        return people;
    }

    private static void addPerson(Table table, String name, Integer age) {
        Map<Attribute, String> personAttributes = new HashMap<>();
        personAttributes.put(createAttribute("name", DatabaseTypes.CHAR), name);
        personAttributes.put(createAttribute("age", DatabaseTypes.INT), age.toString());
        table.addEntry(Entry.create(personAttributes));
    }

    private static Set<Attribute> createPeopleAttributes() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(createAttribute("name", DatabaseTypes.CHAR));
        attributes.add(createAttribute("age", DatabaseTypes.INT));
        return attributes;
    }

    private static Attribute createAttribute(String name, DatabaseTypes type) {
        return new Attribute(name, type);
    }

}

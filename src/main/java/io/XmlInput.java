package io;

import database.Attribute;
import database.DatabaseTypes;
import database.Entry;
import database.Table;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

final class XmlInput {

    Table readFile(File file) throws IOException {
        Document doc = getXmlDocument(file);
        if (doc == null) {
            return null;
        }

        return parseXmlDocument(doc);
    }

    private Document getXmlDocument(File file) throws IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println("xml configuration error: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        Document doc;
        try {
            doc = builder.parse(file);
        } catch (SAXException ex) {
            System.out.println("xml parsing error: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        return doc;
    }

    private Table parseXmlDocument(Document xml){
        Element root = xml.getDocumentElement();

        Element name = (Element)root.getElementsByTagName("name").item(0);
        String tableName = parseTextNode(name);

        Element attributes = (Element)root.getElementsByTagName("attributes");
        Set<Attribute> tableAttributes = parseAttributes(attributes);

        Table table = new Table(tableName, tableAttributes);

        Element entries = (Element)root.getElementsByTagName("entries");
        List<Entry> tableEntries = parseEntries(entries);

        table.addAllEntries(tableEntries);
        return table;
    }

    private String parseTextNode(Element name){
        return ((CharacterData)name.getChildNodes().item(0)).getData();
    }

    private Set<Attribute> parseAttributes(Element attributes){
        Set<Attribute> tableAttributes = new HashSet<>();
        for (Element attribute = (Element)attributes.getFirstChild(); attribute != null; attribute = (Element)attribute.getNextSibling()){
            tableAttributes.add(parseAttribute(attribute));
        }
        return tableAttributes;
    }

    private Attribute parseAttribute(Element attribute){
        Element name = (Element)attribute.getElementsByTagName("name").item(0);
        String attributeName = parseTextNode(name);
        Element type = (Element)attribute.getElementsByTagName("type").item(0);
        String attributeType = parseTextNode(type);

        return new Attribute(attributeName, DatabaseTypes.valueOf(attributeType));
    }

    private List<Entry> parseEntries(Element entries){
        List<Entry> tableEntries = new ArrayList<>();
        for (Element entry = (Element)entries.getFirstChild(); entry != null; entry = (Element)entry.getNextSibling()){
            tableEntries.add(parseEntry(entry));
        }
        return tableEntries;
    }

    private Entry parseEntry(Element entry){
        Map<Attribute, String> tableEntry = new HashMap<>();
        for (Element field = (Element)entry.getFirstChild(); field != null; field = (Element)field.getNextSibling()){
            tableEntry.put(parseAttribute(field), parseValue(field));
        }
        return new Entry(tableEntry);
    }

    private String parseValue(Element field){
        Element value = (Element)field.getElementsByTagName("value").item(0);
        return parseTextNode(value);
    }
}

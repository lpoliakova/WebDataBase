package io;

import database.Attribute;
import database.Entry;
import database.Table;
import org.w3c.dom.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Set;

class XmlOutput {

    void writeFile(File file, Table table) throws FileNotFoundException{
        Document doc = XmlUtilities.getDocumentToWrite();
        addDtd(doc);
        writeXmlDocument(doc, table);
        writeDocument(doc, file);
    }

    private void addDtd(Document xml) {
        DOMImplementation domImpl = xml.getImplementation();
        DocumentType doctype = domImpl.createDocumentType("table", "", "TableConfig.dtd");
        xml.appendChild(doctype);
    }

    private void writeXmlDocument(Document xml, Table table){
        Element root = xml.createElement("table");
        xml.appendChild(root);

        Element name = xml.createElement("name");
        root.appendChild(name);
        writeName(name, table.getName(), xml);

        Element attributes = xml.createElement("attributes");
        root.appendChild(attributes);
        writeAttributes(attributes, table.getAttributes(), xml);

        Element entries = xml.createElement("entries");
        root.appendChild(entries);
        writeEntries(entries, table.getEntries(), xml);
    }

    private void writeName(Element node, String name, Document doc){
        writeTextNode(name, node, doc);
    }

    private void writeTextNode(String text, Element node, Document doc){
        Text textNode = doc.createTextNode(text);
        node.appendChild(textNode);
    }

    private void writeAttributes(Element node, Set<Attribute> attributes, Document doc){
        for (Attribute tableAttribute : attributes) {
            Element attribute = doc.createElement("attribute");
            node.appendChild(attribute);
            writeAttribute(attribute, tableAttribute, doc);
        }
    }

    private void writeAttribute(Element node, Attribute attribute, Document doc) {
        Element name = doc.createElement("name");
        node.appendChild(name);
        writeTextNode(attribute.getName(), name, doc);

        Element type = doc.createElement("type");
        node.appendChild(type);
        writeTextNode(attribute.getType().toString(), type, doc);
    }

    private void writeEntries(Element node, List<Entry> entries, Document doc){
        for (Entry tableEntry : entries) {
            Element entry = doc.createElement("entry");
            node.appendChild(entry);
            writeEntry(entry, tableEntry, doc);
        }
    }

    private void writeEntry(Element node, Entry entry, Document doc) {
        for (Map.Entry<Attribute, String> tableField : entry.getEntry().entrySet()){
            Element field = doc.createElement("field");
            node.appendChild(field);
            writeField(field, tableField, doc);
        }
    }

    private void writeField(Element node, Map.Entry<Attribute, String> field, Document doc) {
        writeAttribute(node, field.getKey(), doc);
        Element value = doc.createElement("value");
        node.appendChild(value);
        writeTextNode(field.getValue(), value, doc);
    }

    static void writeDocument(Document doc, File file) throws FileNotFoundException{
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doc.getDoctype().getSystemId());

            Result output = new StreamResult(file);
            Source input = new DOMSource(doc);

            transformer.transform(input, output);
        } catch (TransformerConfigurationException ex) {
            System.out.println("xml configuration error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (TransformerException ex) {
            System.out.println("xml write down error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

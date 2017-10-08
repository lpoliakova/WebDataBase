package io;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

class XmlUtilities {
    private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    static Document getDocumentToRead(File file) throws IOException {
        DocumentBuilder builder = getDocumentBuilder();
        if (builder == null) return null;
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

    static Document getDocumentToWrite() {
        DocumentBuilder builder = getDocumentBuilder();
        if (builder == null) {
            return null;
        }
        return builder.newDocument();
    }

    private static DocumentBuilder getDocumentBuilder(){
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println("xml configuration error: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        return builder;
    }
}

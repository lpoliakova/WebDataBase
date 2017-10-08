package io;

import org.w3c.dom.Document;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;

public class FileIO {
    public static void createDirectory(String name){
        //TODO: implement directory creation
    }

    public static void deleteFile(String schemaNmae, String name) {
        //TODO: delete file
    }

    static void writeDocument(Document doc, File file) throws FileNotFoundException{
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

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

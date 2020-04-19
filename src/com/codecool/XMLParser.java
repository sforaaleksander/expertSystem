package com.codecool;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public abstract class XMLParser {
    Document doc;

    protected void loadXmlDocument(String xmlPath){
        try {
            File file = new File(xmlPath);
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            this.doc = dBuilder.parse(file);
            this.doc.getDocumentElement().normalize();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}

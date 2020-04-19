package com.codecool;

import com.codecool.models.Fact;
import com.codecool.repository.FactRepository;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FactParser extends XMLParser{
    private FactRepository factRepository;

    FactParser(){
        this.factRepository = new FactRepository();
    }

    public FactRepository getFactRepository() {
        return factRepository;
    }

    public void parse(){
        this.loadXmlDocument("facts.xml");

        NodeList nList = doc.getElementsByTagName("Fact");

        for (int i = 0; i < nList.getLength(); i++){
            Node node = nList.item(i);
            
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element element = (Element) node;
            String elementId = element.getAttribute("id");

            Element subElement = (Element) element.getElementsByTagName("Description").item(0);
            String elementDescription = subElement.getAttribute("value");

            // System.out.println("ID: " + elementId);

            // System.out.println("DESCRIPTION: " + elementDescription);

            Fact fact = new Fact(elementId, elementDescription);
            setFactValues(fact, element);

            this.factRepository.addFact(fact);
        }
    }

    private void setFactValues(Fact fact, Element element){
        Element valuesNode = (Element) element.getElementsByTagName("Evals").item(0);
        
        NodeList valuesList = valuesNode.getElementsByTagName("Eval");

        for (int i = 0; i < valuesList.getLength(); i++) {
            Element value = (Element) valuesList.item(i);
            String id = value.getAttribute("id");
            String worth = value.getTextContent();
            fact.setValueById(id, Boolean.valueOf(worth));
        }
    }
}

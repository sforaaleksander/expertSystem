package com.codecool;

import java.util.Arrays;
import java.util.List;

import com.codecool.models.Answer;
import com.codecool.models.MultipleValue;
import com.codecool.models.Question;
import com.codecool.models.SingleValue;
import com.codecool.models.Value;
import com.codecool.repository.RuleRepository;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RuleParser extends XMLParser {
    private RuleRepository ruleRepository;

    public RuleParser() {
        this.ruleRepository = new RuleRepository();
    }

    public RuleRepository getRuleRepository() {
        return ruleRepository;
    }

    public void parse() {
        this.loadXmlDocument("rules.xml");

        NodeList nList = doc.getElementsByTagName("Rule");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element element = (Element) node;
            String id = element.getAttribute("id");

            String questionText = element.getElementsByTagName("Question").item(0).getTextContent();

            // System.out.println(questionText);
            Answer answer = getAnswers(element);

            Question question = new Question(id, questionText, answer);
            this.ruleRepository.addQuestion(question);
        }
    }

    private Answer getAnswers(Element element) {
        Answer answer = new Answer();
        NodeList answerList = element.getElementsByTagName("Selection");

        for (int i = 0; i < answerList.getLength(); i++) {
            Node answerNode = answerList.item(i);
            if (answerNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element answerElement = (Element) answerNode;
            Element valueNode = (Element) answerNode.getChildNodes().item(1);
            boolean answerType = Boolean.valueOf(answerElement.getAttribute("value"));
            Value value;
            
            if (valueNode.getNodeName().equals("SingleValue")){
                String param = valueNode.getAttribute("value");
                value = new SingleValue(param, answerType);
            } else {
                List<String> params = Arrays.asList(valueNode.getAttribute("value").split(","));
                value = new MultipleValue(params, answerType);
            }
            answer.addValue(value);
        }
        return answer;
    }

}

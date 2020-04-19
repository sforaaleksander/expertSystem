package com.codecool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.codecool.models.Fact;
import com.codecool.models.Question;

public class ESProvider {
    Scanner scan;
    FactParser factParser;
    RuleParser ruleParser;
    Map<String, Boolean> userSelection;

    public ESProvider(FactParser factParser, RuleParser ruleParser) {
        scan = new Scanner(System.in);
        this.factParser = factParser;
        this.ruleParser = ruleParser;
        this.userSelection = new HashMap<>();
    }

    public void collectAnswers() {
        factParser.parse();
        ruleParser.parse();
        Iterator<Question> questionIterator = this.ruleParser.getRuleRepository().getIterator();

        while (questionIterator.hasNext()) {
            Question question = questionIterator.next();
            String questionId = question.getId();
            System.out.println(question.getQuestion());
            boolean userAnswer = getAnswerByQuestion(questionId, question);

            this.userSelection.put(questionId, userAnswer);
        }
        scan.close();
    }

    public boolean getAnswerByQuestion(String questionId, Question question) {
        String userInput = scan.next();
        return question.getEvaluatedAnswer(userInput);
    }

    public String evaluate() {
        String result = "";
        Iterator<Fact> factIterator = this.factParser.getFactRepository().getIterator();
        Fact fact = null;
        while (factIterator.hasNext()) {
            fact = factIterator.next();
            if (!checkMatch(fact.getIdSet(), fact)) {
                continue;
            }
            result = fact.getDescription();
        }
        return result;
    }

    private boolean checkMatch(Set<String> idSet, Fact fact) {

        for (String id : idSet) {
            if (fact.getValueById(id) != this.userSelection.get(id)) {
                return false;
            }
        }
        return true;
    }

}

package com.codecool;

public class Main {
    public static void main(String[] args){
        
        FactParser factParser = new FactParser();
        RuleParser ruleParser = new RuleParser();
        ESProvider esProvider = new ESProvider(factParser, ruleParser);
        esProvider.collectAnswers();

        String result = esProvider.evaluate();

        if (result.equals("")) {
            result = "Could not find any matches, please provide another values.";
        }
        
        System.out.println(result);
    }
 }
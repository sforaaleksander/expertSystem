package com.codecool.models;

import java.util.InputMismatchException;

public class Question {
    private String id;
    private String question;
    private Answer answer;

    public Question(String id, String question, Answer answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public boolean getEvaluatedAnswer(String input) {
        boolean evaluation = false;
        try {
            evaluation = this.answer.evaluateAnswerByInput(input);
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
        return evaluation;
    }
}

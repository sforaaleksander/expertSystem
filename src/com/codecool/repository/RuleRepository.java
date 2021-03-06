package com.codecool.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.codecool.models.Question;

public class RuleRepository {
    private Iterator<Question> questionIterator;
    private List<Question> questions;

    public RuleRepository() {
        this.questionIterator = new QuestionIterator();
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    private class QuestionIterator implements Iterator<Question> {
        int index;

        @Override
        public boolean hasNext() {
            return index < questions.size();
        }

        @Override
        public Question next() {
            if (this.hasNext()) {
                return questions.get(index++);
            }
            return null;
        }
    }

    public Iterator<Question> getIterator() {
        return questionIterator;
    }
}

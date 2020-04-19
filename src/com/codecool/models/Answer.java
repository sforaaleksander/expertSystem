package com.codecool.models;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Answer {

    private List<Value> values;

    public Answer(){
        this.values = new ArrayList<>();
    }

    public void addValue(Value value){
        values.add(value);
    }

    public List<Value> getValue() {
        return values;
    }

    public boolean evaluateAnswerByInput(String input) throws InputMismatchException {
        
        for (Value value : this.values) {
            if (value.getInputPattern().contains(input)) {
                return value.getSelectionType();
            }
        }
        throw new InputMismatchException();
    }


}
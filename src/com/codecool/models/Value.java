package com.codecool.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Value {
    private List<String> inputPattern;
    private boolean selectionType;

    public Value(String pattern, boolean selectionType){
        this.inputPattern = new ArrayList<String>();
        inputPattern.add(pattern);
        this.selectionType = selectionType;
    }

    public Value(List<String> patterns, boolean selectionType){
        this.inputPattern = patterns;
        this.selectionType = selectionType;
    }

    public void addPattern(String pattern){
        inputPattern.add(pattern);
    }

    public List<String> getInputPattern(){
        return inputPattern;
    }

    public boolean getSelectionType(){
        return selectionType;
    }
}

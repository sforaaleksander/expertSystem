package com.codecool.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.codecool.models.Fact;

public class FactRepository {
    private Iterator<Fact> factIterator;
    private List<Fact> facts;

    public FactRepository(){
        this.factIterator = new FactIterator();
        this.facts = new ArrayList<>();
    }

    public void addFact(Fact fact) {
        facts.add(fact);
    }

    private class FactIterator implements Iterator<Fact> {
        int index;

        @Override
        public boolean hasNext() {
            return index < facts.size();
        }

        @Override
        public Fact next() {
            if (this.hasNext()) {
                return facts.get(index++);
            }
            return null;
        }
    }

    public Iterator<Fact> getIterator() {
        return factIterator;
    }
}
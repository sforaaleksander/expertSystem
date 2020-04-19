package com.codecool.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Fact {
    private String id;
    private String description;
    private Map<String, Boolean> values;

    public Fact(String id, String description) {
        this.id = id;
        this.description = description;
        this.values = new HashMap<>();
    }

    public Set<String> getIdSet() {
        return values.keySet();
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public void setValueById(String id, boolean value) {
        values.put(id, value);
    }

    public boolean getValueById(String id) {
        return values.get(id);
    }
}

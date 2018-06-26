package com.integrate.todo;

public class Item {

    private Integer ID;
    private String description;

    public Item() {
    }

    public Integer getID() {
        return ID;
    }

    public Item setID(Integer ID) {
        this.ID = ID;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }
}

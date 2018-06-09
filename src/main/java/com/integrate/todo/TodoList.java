package com.integrate.todo;

import java.util.Objects;

public class TodoList {

    private String title;
    private Integer listID;

    public TodoList(){}

    public Integer getListID() {
        return listID;
    }

    public TodoList setListID(Integer listID) {
        this.listID = listID;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TodoList setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoList todoList = (TodoList) o;
        return Objects.equals(title, todoList.title) &&
                Objects.equals(listID, todoList.listID);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, listID);
    }
}

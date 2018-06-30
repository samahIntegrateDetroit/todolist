package com.integrate.todo.db;

import com.integrate.todo.Item;
import com.integrate.todo.TodoList;

import java.util.List;

public interface DBWrapperList {

    public TodoList createList(TodoList todoList);

    TodoList findListById(int id);

    TodoList updateListTitle(Integer listID, String newTitle);

    TodoList archiveListById(TodoList todoList);

    List<Item> getListItemsByID(int listID);

    TodoList deleteList(int listID);
}

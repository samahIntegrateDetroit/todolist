package com.integrate.todo.db;

import com.integrate.todo.TodoList;

public interface DBWrapper {

    public TodoList createList(TodoList todoList);

    TodoList findListById(int id);

    TodoList updateListTitle(int id, String newTitle);
}

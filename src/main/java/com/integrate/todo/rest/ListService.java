package com.integrate.todo.rest;

import com.integrate.todo.Item;
import com.integrate.todo.TodoList;
import com.integrate.todo.db.DBWrapperItem;
import com.integrate.todo.db.DBWrapperList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListService {

        private DBWrapperList listDB;
        private DBWrapperItem itemDB;

    @Autowired
    public ListService(DBWrapperList database, DBWrapperItem itemDB) {
        this.listDB = database;
        this.itemDB = itemDB;
    }


    public TodoList getList(Integer id) {
        return listDB.findListById(id);
    }

    public TodoList createTodoList(TodoList todoList) {
        return listDB.createList(todoList);
    }


    public TodoList updateList(Integer listID, String newTitle) {
        return listDB.updateListTitle(listID, newTitle);
    }

    public TodoList archiveList(TodoList todoList) {
        return listDB.archiveListById(todoList);
    }

    public Item createItem(Item item, int listID) {
        return this.itemDB.addItemToList(item, listID);
    }
}




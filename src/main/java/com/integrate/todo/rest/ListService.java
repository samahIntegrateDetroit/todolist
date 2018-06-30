package com.integrate.todo.rest;

import com.integrate.todo.Item;
import com.integrate.todo.TodoList;
import com.integrate.todo.db.DBWrapperList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListService {

        private DBWrapperList database;

        @Autowired
        public ListService(DBWrapperList database ) {
            this.database = database;
        }


        public TodoList getList(Integer id) {
            return database.findListById(id);
        }

        public TodoList createTodoList(TodoList todoList) {
            return database.createList(todoList);
        }


        public TodoList updateList(Integer listID, String newTitle) {
            return database.updateListTitle(listID, newTitle);
    }

        public TodoList archiveList(TodoList todoList) {
            return database.archiveListById(todoList);
        }

        public List<Item> getListItems(int listID) {
            return database.getListItemsByID(listID);
    }

        public TodoList deleteList(int listID) { return database.deleteList(listID); }
}




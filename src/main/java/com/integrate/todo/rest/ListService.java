package com.integrate.todo.rest;

import com.integrate.todo.TodoList;
import com.integrate.todo.db.DBWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListService {

        private DBWrapper database;

        @Autowired
        public ListService(DBWrapper database ) {
            this.database = database;
        }


        public TodoList getList(Integer id) {
            return database.findListById(id);
        }

        public TodoList createTodoList(TodoList todoList) {
            return database.createList(todoList);
        }


    }




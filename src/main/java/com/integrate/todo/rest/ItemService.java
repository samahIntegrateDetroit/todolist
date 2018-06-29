package com.integrate.todo.rest;

import com.integrate.todo.Item;
import com.integrate.todo.db.DBWrapperItem;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemService {
    private DBWrapperItem database;

    @Autowired
    public ItemService(DBWrapperItem database ) {
        this.database = database;
    }


    public Item createItem(Item item) {
        return database.createItem(item);
    }
}

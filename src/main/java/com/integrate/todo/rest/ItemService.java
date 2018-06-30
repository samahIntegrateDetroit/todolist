package com.integrate.todo.rest;

import com.integrate.todo.Item;
import com.integrate.todo.db.DBWrapperItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemService {
    private DBWrapperItem database;

    @Autowired
    public ItemService(DBWrapperItem database ) {
        this.database = database;
    }

    public Item createItem(Item item) {
        return database.createItem(item);
    }

    public Item getItem(Integer id) {
        return database.findItemById(id);
    }

    public Item updateItem(Item item) {
        return database.updateItem(item);
    }
}

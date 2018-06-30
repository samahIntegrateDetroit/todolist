package com.integrate.todo.db;

import com.integrate.todo.Item;

public interface DBWrapperItem {
    Item createItem(Item item);

    Item findItemById(int id);

    Item updateItem(Item item);
}

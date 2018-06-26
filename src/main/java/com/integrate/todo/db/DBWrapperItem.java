package com.integrate.todo.db;

import com.integrate.todo.Item;

public interface DBWrapperItem {

    Item addItemToList(Item item, Integer ListID);

}

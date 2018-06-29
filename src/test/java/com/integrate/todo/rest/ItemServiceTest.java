package com.integrate.todo.rest;

import com.integrate.todo.Item;
import com.integrate.todo.db.SQLiteItem;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ItemServiceTest {

    private SQLiteItem db;

    private ItemService service;

    @Before
    public void setup() {

        this.db = mock(SQLiteItem.class);

        this.service = new ItemService(this.db);

    }

    @Test
    public void createItem_savesToRepository() {
        int expectedID = 3;
        int expectedListID = 4;
        String expectedDescription = "Specific value";

        Item inputItem = new Item()
                .setListID(expectedListID).setDescription(expectedDescription);

        Item expectedItem = new Item()
                .setItemID(expectedID).setListID(expectedListID).setDescription(expectedDescription);

        when(this.db.createItem(inputItem))
                .thenReturn(expectedItem);

        Item item = this.service.createItem(
                new Item().setListID(expectedListID).setDescription(expectedDescription)
        );

        verify(db)
                .createItem(inputItem);

        assertThat(item)
                .isEqualTo(expectedItem);
    }

}
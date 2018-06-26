package com.integrate.todo.rest;

import com.integrate.todo.Item;
import com.integrate.todo.TodoList;
import com.integrate.todo.db.SQLiteItem;
import com.integrate.todo.db.SQLiteList;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListServiceTest {


        private SQLiteList mockListDB;


        private ListService service;
        private SQLiteItem mockItemDB;

        @Before
        public void setup() {
            this.mockListDB = mock(SQLiteList.class);
            this.mockItemDB = mock(SQLiteItem.class);
            this.service = new ListService(this.mockListDB, this.mockItemDB);

        }

        @Test
        public void createTodoList_savesToRepository() {
            int expectedID = 3;
            int expectedUserID = 4;
            String expectedTitle = "Specific value";
            String expectedArchiveStatus = "";


            TodoList inputList = new TodoList()
                    .setUserID(expectedUserID).setTitle(expectedTitle).setArchiveStatus(expectedArchiveStatus);

            TodoList expectedTodoList = new TodoList()
                    .setListID(expectedID).setUserID(expectedUserID).setTitle(expectedTitle).setArchiveStatus(expectedArchiveStatus);

            when(this.mockListDB.createList(inputList))
                    .thenReturn(expectedTodoList);

            TodoList todoList = this.service.createTodoList(
                    new TodoList().setUserID(expectedUserID).setTitle(expectedTitle).setArchiveStatus(expectedArchiveStatus)
            );

            verify(mockListDB)
                    .createList(inputList);

            assertThat(todoList)
                    .isEqualTo(expectedTodoList);
        }

        @Test
        public void getList_returnsListWithCorrectID() {
            int expectedListID = 1;
            int expectedUserID = 4;
            String expectedTitle = "Specific value";
            String expectedArchiveStatus = "";

            TodoList expected_list = new TodoList()
                    .setListID(expectedListID)
                    .setUserID(expectedUserID)
                    .setTitle(expectedTitle)
                    .setArchiveStatus(expectedArchiveStatus);


            when(this.mockListDB.findListById(expectedListID))
                    .thenReturn(expected_list);

            TodoList list = this.service.getList(expectedListID);

            verify(this.mockListDB)
                    .findListById(expectedListID);

            assertThat(list)
                    .isEqualTo(expected_list);
        }

        @Test
        public void archiveList_returnsArchivedList() {
            int expectedListID = 1;
            int expectedUserID = 4;
            String expectedTitle = "Specific value";
            String expectedArchiveStatus = "Y";

            TodoList expected_list = new TodoList()
                    .setListID(expectedListID)
                    .setUserID(expectedUserID)
                    .setTitle(expectedTitle)
                    .setArchiveStatus(expectedArchiveStatus);

            TodoList inputList = new TodoList()
                    .setListID(expectedListID)
                    .setUserID(expectedUserID)
                    .setTitle(expectedTitle)
                    .setArchiveStatus("");


            when(this.mockListDB.archiveListById(inputList))
                    .thenReturn(expected_list);

            TodoList list = this.service.archiveList(inputList);

            verify(this.mockListDB)
                    .archiveListById(inputList);

            assertThat(list.getArchiveStatus())
                    .isEqualTo(expectedArchiveStatus);


        }

    @Test
    public void updateList_returnsUpdatedListWithCorrectID() {
        int expectedListID = 1;
        String title = "New Title";
        TodoList expected_list = new TodoList();

        when(this.mockListDB.updateListTitle(expectedListID, title))
                .thenReturn(expected_list);

        TodoList list = this.service.updateList(expectedListID, title );

        verify(this.mockListDB)
                .updateListTitle(expectedListID, title);

        assertThat(expected_list).isEqualTo(list);
    }

    @Test
    public void addItem_returnsItemFromDB(){
        String description = "hello";
        Item itemPassedIn = new Item().setDescription(description);
        int listID = 3;


        Item expectedItem = new Item().setDescription(description).setID(4);
        when(mockItemDB.addItemToList(itemPassedIn,listID)).thenReturn(expectedItem);

        Item item = this.service.createItem(itemPassedIn, listID);

        verify(mockItemDB).addItemToList(itemPassedIn, listID);

        assertThat(item).isEqualTo(expectedItem);
    }


    }


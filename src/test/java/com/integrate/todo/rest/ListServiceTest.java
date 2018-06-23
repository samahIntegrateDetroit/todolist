package com.integrate.todo.rest;

import com.integrate.todo.TodoList;
import com.integrate.todo.db.SQLiteList;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListServiceTest {


        private SQLiteList db;

        private ListService service;

        @Before
        public void setup() {

            this.db = mock(SQLiteList.class);

            this.service = new ListService(this.db);

        }

        @Test
        public void createTodoList_savesToRepository() {
            int expectedID = 3;
            String expectedTitle = "Specific value";

            TodoList inputList = new TodoList().setTitle(expectedTitle);

            TodoList expectedTodoList = new TodoList()
                    .setTitle(expectedTitle).setListID(expectedID);

            when(this.db.createList(inputList))
                    .thenReturn(expectedTodoList);

            TodoList todoList = this.service.createTodoList(
                    new TodoList().setTitle(expectedTitle)
            );


            verify(db)
                    .createList(inputList);

            assertThat(todoList.getListID())
                    .isEqualTo(expectedID);
        }

        @Test
        public void getList_returnsListWithCorrectID() {
            int expectedListID = 1;
            TodoList expected_list = new TodoList()
                    .setTitle("")
                    .setListID(expectedListID);

            when(this.db.findListById(expectedListID))
                    .thenReturn(expected_list);

            TodoList list = this.service.getList(expectedListID);

            verify(this.db)
                    .findListById(expectedListID);

            assertThat(list.getListID())
                    .isEqualTo(expectedListID);
        }

    @Test
    public void updateList_returnsUpdatedListWithCorrectID() {
        int expectedListID = 1;
        String title = "New Title";
        TodoList expected_list = new TodoList();

        when(this.db.updateListTitle(expectedListID, title))
                .thenReturn(expected_list);

        TodoList list = this.service.updateList(expectedListID, title );

        verify(this.db)
                .updateListTitle(expectedListID, title);

        assertThat(expected_list).isEqualTo(list);
    }

    }


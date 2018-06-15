package com.integrate.todo.rest;

import com.integrate.todo.TodoList;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListControllerTest {
    @Test
    public void createList_returnsHttpStatusCreated() {
        ListService mockService = mock( ListService.class );
        ListController todoListController = new ListController(mockService);

        TodoList expectedTodoList = new TodoList();
        TodoList todoListPassedIn = new TodoList();


        when( mockService.createTodoList( todoListPassedIn ) )
                .thenReturn( expectedTodoList );
        ResponseEntity expectedResponseEntity = new ResponseEntity<>(
                expectedTodoList, HttpStatus.CREATED);

        ResponseEntity<TodoList> responseEntity = todoListController.createList( todoListPassedIn );


        verify( mockService )
                .createTodoList( todoListPassedIn );

        assertThat( responseEntity )
                .isEqualTo( expectedResponseEntity );
    }

    @Test
    public void getList_returnsListAndHttpStatus200() {


        ListService mockService = mock( ListService.class );
        ListController todoListController = new ListController( mockService );

        TodoList expectedTodoList = new TodoList().setListID( 1 );

        when( mockService.getList( 1 ) )
                .thenReturn( expectedTodoList );

        ResponseEntity<TodoList> resultList = todoListController.readList(1);
        ResponseEntity expectedResponse = new ResponseEntity<>( // compare what I an getting back to what I expect
                expectedTodoList,
                HttpStatus.OK
        );


        verify( mockService )
                .getList( 1 );

        assertThat( resultList )
                .isEqualTo( expectedResponse );
    }

    @Test
    public void getList_whenDoesntExist_returnsHttpStatus204(){
        ListService mockService = mock( ListService.class );
        ListController todoListController = new ListController( mockService );

        TodoList expectedTodoList = new TodoList().setListID( -1 );
        int input_id = 8;

        when( mockService.getList( input_id ) )
                .thenReturn( expectedTodoList );

        ResponseEntity expectedResponse = new ResponseEntity<>(
                expectedTodoList,
                HttpStatus.NO_CONTENT
        );

        ResponseEntity<TodoList> resultList = todoListController.readList( input_id );


        verify( mockService )
                .getList( input_id );

        assertThat( resultList )
                .isEqualTo( expectedResponse );
    }

    @Test
    public void updateListTitle_updatesListTitle_returnsHttpStatus200() {
        ListService mockService = mock( ListService.class );
        ListController todoListController = new ListController(mockService);
        String newTitle = "New Title";
        int listID = 1;
        TodoList todoList = new TodoList().setTitle(newTitle).setListID(listID);

        when (mockService.updateList(listID, newTitle))
                .thenReturn( todoList );


        ResponseEntity<TodoList> responseEntity = todoListController.updateList(listID,newTitle);
        TodoList body = responseEntity.getBody();

        verify(mockService).updateList(listID, newTitle);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body.getTitle()).isEqualTo(newTitle);
        assertThat(body.getListID()).isEqualTo(listID);

    }
}
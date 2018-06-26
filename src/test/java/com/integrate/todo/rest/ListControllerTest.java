package com.integrate.todo.rest;

import com.integrate.todo.Item;
import com.integrate.todo.TodoList;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

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

        ResponseEntity expectedResponse = new ResponseEntity<>(
                expectedTodoList, HttpStatus.OK);

        when( mockService.getList( 1 ) )
                .thenReturn( expectedTodoList );

        ResponseEntity<TodoList> resultList = todoListController.readList(1);


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
        HashMap<String, Object> hashMap = new HashMap<>();
        String newTitle = "New Title";
        int listID = 1;
        hashMap.put("id", listID);
        hashMap.put("updatedTitle", newTitle);
        TodoList todoList = new TodoList().setTitle(newTitle).setListID(listID);

        when (mockService.getList(listID))
                .thenReturn( todoList );

        when (mockService.updateList(listID, newTitle))
                .thenReturn( todoList );


        ResponseEntity<TodoList> responseEntity = todoListController.updateList(listID, hashMap);
        TodoList body = responseEntity.getBody();

        verify(mockService).updateList(listID, newTitle);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body.getTitle()).isEqualTo(newTitle);
        assertThat(body.getListID()).isEqualTo(listID);

    }

    @Test
    public void updateListTitle_DoesNOTupdateListTitle_returnsHttpStatus304(){
        ListService mockService = mock( ListService.class );
        ListController todoListController = new ListController(mockService);

        HashMap<String, Object> hashMap = new HashMap<>();
        String newTitle = "";
        int listIDToReturn = -1;
        int listIDToPassIn = 5;
        hashMap.put("id", listIDToPassIn);
        hashMap.put("updatedTitle", "Hello!");
        TodoList todoList = new TodoList().setTitle(newTitle).setListID(listIDToReturn);

        when (mockService.getList(listIDToPassIn))
                .thenReturn( todoList );

        ResponseEntity<TodoList> responseEntity = todoListController.updateList(listIDToPassIn, hashMap);
        TodoList body = responseEntity.getBody();

        verify(mockService).getList(listIDToPassIn);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_MODIFIED);
        assertThat(body.getListID()).isEqualTo(listIDToReturn);

    }

    @Test
    public void archiveList_returnsArchivedListAndHttpStatus200() {
        int expectedListID = 1;
        int expectedUserID = 4;
        String expectedTitle = "Specific value";
        String expectedArchiveStatus = "Y";

        int listIDToPassIn = 1;

        ListService mockService = mock( ListService.class );
        ListController todoListController = new ListController( mockService );

        TodoList expectedTodoList = new TodoList()
                .setListID(expectedListID)
                .setUserID(expectedUserID)
                .setTitle(expectedTitle)
                .setArchiveStatus(expectedArchiveStatus);

        TodoList inputList = new TodoList()
                .setListID(expectedListID)
                .setUserID(expectedUserID)
                .setTitle(expectedTitle)
                .setArchiveStatus("");

        ResponseEntity expectedResponse = new ResponseEntity<>(
                expectedTodoList, HttpStatus.OK);

        when( mockService.getList(listIDToPassIn) )
                .thenReturn( inputList );

        when( mockService.archiveList(inputList ) )
                .thenReturn( expectedTodoList );

        ResponseEntity<TodoList> resultList = todoListController.archiveList(listIDToPassIn);

        verify( mockService )
                .archiveList(inputList );

        assertThat( resultList )
                .isEqualTo( expectedResponse );
    }

    @Test
    public void archiveList_whenListDoesntExist_DoesNotUpdateArchiveStatus_returnsHttpStatus304(){
        int expectedListID = -1;
        int listIDToPassIn = 5;

        ListService mockService = mock( ListService.class );
        ListController todoListController = new ListController( mockService );

        TodoList expectedTodoList = new TodoList()
                .setListID(expectedListID);

        ResponseEntity expectedResponse = new ResponseEntity<>(
                expectedTodoList, HttpStatus.NOT_MODIFIED);

        when( mockService.getList(listIDToPassIn) )
                .thenReturn( expectedTodoList );

        ResponseEntity<TodoList> resultList = todoListController.archiveList(listIDToPassIn);

        verify( mockService )
                .getList(listIDToPassIn );

        assertThat( resultList )
                .isEqualTo( expectedResponse );
    }
    
    @Test
    public void addItem_givesHTTPStatusOK_givenAddSuccess(){

        String description = "new item";
        ListService mockService = mock(ListService.class);
        ListController listControl = new ListController(mockService);
        Item item = new Item().setDescription(description);

        Integer listID = 5;
        Item expectedItem = new Item().setDescription(description).setID(1);

        when( mockService.createItem(item, listID) )
                .thenReturn( expectedItem );

        ResponseEntity<Item> responseItem = listControl.createItem(item, listID);

        verify(mockService).createItem(item, listID);
        assertThat(responseItem.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseItem.getBody()).isEqualTo(expectedItem);
    }

    @Test
    public void addItem_givesHTTPStatusNoContent_givenAddFailure(){
        ListService mockService = mock(ListService.class);
        ListController listController = new ListController(mockService);

        Item itemPassedIn = new Item().setDescription("hi");

        Item expectedItem = new Item().setDescription("hi").setID(-1);
        int listID = 9999;

        when(mockService.createItem(itemPassedIn, listID)).thenReturn(expectedItem);

        ResponseEntity<Item> responseItem = listController.createItem(itemPassedIn, listID);

        verify(mockService).createItem(itemPassedIn,listID);

        assertThat(responseItem.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(responseItem.getBody()).isEqualTo(expectedItem);
    }
}
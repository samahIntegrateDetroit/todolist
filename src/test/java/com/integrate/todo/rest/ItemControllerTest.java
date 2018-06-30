package com.integrate.todo.rest;

import com.integrate.todo.Item;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ItemControllerTest {
    @Test
    public void createItem_returnsHttpStatusCreated() {
        ItemService mockService = mock( ItemService.class );
        ItemController itemController = new ItemController(mockService);

        Item expectedItem = new Item();
        Item itemPassedIn = new Item();


        when( mockService.createItem( itemPassedIn ) )
                .thenReturn( expectedItem );
        ResponseEntity expectedResponseEntity = new ResponseEntity<>(
                expectedItem, HttpStatus.CREATED);

        ResponseEntity<Item> responseEntity = itemController.createItem( itemPassedIn );


        verify( mockService )
                .createItem( itemPassedIn );

        assertThat( responseEntity )
                .isEqualTo( expectedResponseEntity );
    }

    @Test
    public void getItem_returnsItemAndHttpStatus200() {
        int expectedID = 3;
        int expectedListID = 4;
        String expectedDescription = "Specific value";

        ItemService mockService = mock( ItemService.class );
        ItemController itemController = new ItemController( mockService );

        Item expectedItem = new Item().setItemID(expectedID ).setListID(expectedListID).setDescription(expectedDescription);

        ResponseEntity expectedResponse = new ResponseEntity<>(
                expectedItem, HttpStatus.OK);

        when( mockService.getItem( 1 ) )
                .thenReturn( expectedItem );

        ResponseEntity<Item> resultItem = itemController.readItem(1);


        verify( mockService )
                .getItem( 1 );

        assertThat( resultItem )
                .isEqualTo( expectedResponse );
    }

    @Test
    public void getItem_whenDoesntExist_returnsHttpStatus204(){
        ItemService mockService = mock( ItemService.class );
        ItemController itemController = new ItemController( mockService );

        Item expectedItem = new Item().setItemID( -1 );
        int input_id = 8;

        when( mockService.getItem( input_id ) )
                .thenReturn( expectedItem );

        ResponseEntity expectedResponse = new ResponseEntity<>(
                expectedItem,
                HttpStatus.NO_CONTENT
        );

        ResponseEntity<Item> resultItem = itemController.readItem( input_id );

        verify( mockService )
                .getItem( input_id );

        assertThat( resultItem )
                .isEqualTo( expectedResponse );
    }

    @Test
    public void updateItemTitle_updatesItemTitle_returnsHttpStatus200() {
        ItemService mockService = mock( ItemService.class );
        ItemController itemController = new ItemController(mockService);

        HashMap<String, Object> hashMap = new HashMap<>();

        int expectedID = 3;
        int expectedListID = 4;
        String expectedDescription = "Specific value";

        hashMap.put("itemID", expectedID);
        hashMap.put("newListID", expectedListID);
        hashMap.put("newDescription", expectedDescription);

        Item inputItem = new Item()
                .setItemID(expectedID)
                .setListID(expectedListID)
                .setDescription(expectedDescription);

        Item expectedItem = new Item()
                .setItemID(expectedID)
                .setListID(expectedListID)
                .setDescription(expectedDescription);

        when (mockService.updateItem(inputItem))
                .thenReturn( expectedItem );

        ResponseEntity<Item> responseEntity = itemController.updateItem(hashMap);

        Item body = responseEntity.getBody();

        verify(mockService).updateItem(inputItem);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body.getDescription()).isEqualTo(expectedDescription);
        assertThat(body.getListID()).isEqualTo(expectedListID);
        assertThat(body.getItemID()).isEqualTo(expectedID);

    }

    @Test
    public void updateItemTitle_DoesNOTupdateItem_returnsHttpStatus304(){
        ItemService mockService = mock( ItemService.class );
        ItemController itemController = new ItemController(mockService);

        HashMap<String, Object> hashMap = new HashMap<>();

        int inputID = 3;
        int inputListID = 4;
        String inputDescription = "Specific value";

        int expectedID = -1;
        int expectedListID = 0;
        String expectedDescription = "";

        hashMap.put("itemID", inputID);
        hashMap.put("newListID", inputListID);
        hashMap.put("newDescription", inputDescription);

        Item inputItem = new Item()
                .setItemID(inputID)
                .setListID(inputListID)
                .setDescription(inputDescription);

        Item expectedItem = new Item()
                .setItemID(expectedID)
                .setListID(expectedListID)
                .setDescription(expectedDescription);

        when (mockService.updateItem(inputItem))
                .thenReturn( expectedItem );

        ResponseEntity<Item> responseEntity = itemController.updateItem(hashMap);
        Item body = responseEntity.getBody();

        verify(mockService).updateItem(inputItem);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_MODIFIED);
        assertThat(body.getItemID()).isEqualTo(expectedID);

    }
}
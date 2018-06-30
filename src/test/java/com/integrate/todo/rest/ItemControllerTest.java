package com.integrate.todo.rest;

import com.integrate.todo.Item;
import com.integrate.todo.Item;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
}
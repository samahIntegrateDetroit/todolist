package com.integrate.todo.rest;

import com.integrate.todo.FolderList;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FolderControllerTest {

    @Test
    public void createList_returnsHttpStatusCreated() {

        FolderService mockService = mock( FolderService.class );
       FolderController FolderController = new FolderController(mockService);

        FolderList expectedFolderList = new FolderList();
        FolderList FolderListPassedIn = new FolderList();


        when( mockService.createFolderList( FolderListPassedIn ) )
                .thenReturn( expectedFolderList );
        ResponseEntity expectedResponseEntity = new ResponseEntity<>(
                expectedFolderList, HttpStatus.CREATED);

        ResponseEntity<FolderList> responseEntity = FolderController.createFolderList( FolderListPassedIn );


        verify( mockService )
                .createFolderList( FolderListPassedIn );

        assertThat( responseEntity )
                .isEqualTo( expectedResponseEntity );
    }

}
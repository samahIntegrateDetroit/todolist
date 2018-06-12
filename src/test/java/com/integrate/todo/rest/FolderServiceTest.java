package com.integrate.todo.rest;

import com.integrate.todo.FolderList;
import com.integrate.todo.db.SQLiteFolder;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FolderServiceTest {

    private SQLiteFolder db;

    private FolderService service;

    @Before
    public void setup() {

        this.db = mock(SQLiteFolder.class);

        this.service = new FolderService(this.db);

    }

    @Test
    public void createFolderList_savesToRepository() {
        int expectedID = 3;
        String expectedTitle = "Specific value";

        FolderList inputList = new FolderList().setTitle(expectedTitle);

        FolderList expectedFolderList = new FolderList()
                .setTitle(expectedTitle).setFolderID(expectedID);

        when(this.db.createFolderList(inputList))
                .thenReturn(expectedFolderList);

        FolderList folderList = this.service.createFolderList(
                new FolderList().setTitle(expectedTitle)
        );

        verify(this.db)
                .createFolderList(inputList);

        assertThat(folderList.getFolderID())
                .isEqualTo(expectedID);
    }

}
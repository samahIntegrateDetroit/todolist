package com.integrate.todo.rest;

import com.integrate.todo.FolderList;
import com.integrate.todo.db.DBWrapperFolder;
import com.integrate.todo.db.SQLiteList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FolderService {

    private DBWrapperFolder database;

    @Autowired
    public FolderService(DBWrapperFolder database) { this.database = database;
    }

    public FolderList createFolderList(FolderList folderList) {
        return database.createFolderList(folderList);
    }
}
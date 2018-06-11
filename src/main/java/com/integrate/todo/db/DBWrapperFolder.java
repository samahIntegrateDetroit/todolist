package com.integrate.todo.db;

import com.integrate.todo.FolderList;

public interface DBWrapperFolder {

    public FolderList createFolderList(FolderList folderList);

    FolderList findFolderID(int id);
}

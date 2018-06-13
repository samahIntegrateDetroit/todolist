package com.integrate.todo.db;

import com.integrate.todo.FolderList;
import com.integrate.todo.db.SQLiteFolder;

public interface DBWrapperFolder {

    public FolderList createFolderList(FolderList folderList);

    FolderList findFolderID(int id);
}

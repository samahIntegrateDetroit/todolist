package com.integrate.todo;

public class FolderList {

    private String title;
    private Integer folderID;

    public FolderList(){}

    public FolderList setFolderID(Integer folderID){
        this.folderID = folderID;
        return this;
    }

    public Integer getFolderID() {return folderID;}

    public FolderList setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitle(){return title;}
}

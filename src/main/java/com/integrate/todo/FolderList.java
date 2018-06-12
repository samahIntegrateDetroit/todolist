package com.integrate.todo;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolderList that = (FolderList) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(folderID, that.folderID);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, folderID);
    }
}

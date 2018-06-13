package com.integrate.todo.db;

import com.integrate.todo.FolderList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sqlite.core.DB;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class SQLiteFolder implements DBWrapperFolder {

    @Autowired
    DataSource dataSource;

    @Override
    public FolderList createFolderList(FolderList folderList) {
        String folderListTitle = folderList.getTitle();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO List (LIST_NAME) VALUES ('" + folderListTitle + "')"
            );
            statement.close();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid()");
            int id = resultSet.getInt("last_insert_rowid()");
            folderList.setFolderID(id);
            connection.close();
            return folderList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        folderList.setFolderID(-1);
        return folderList;
    }

    @Override
    public FolderList findFolderID(int id) {
        FolderList folderList = new FolderList();

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from List WHERE ID=" + id + ";");

            if (resultSet.next() == false) {
                folderList.setFolderID(-1);
            } else {
                folderList.setFolderID(resultSet.getInt("ID"));
                folderList.setTitle(resultSet.getString("LIST_NAME"));
            }
            return folderList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        folderList.setFolderID(-1);

        return folderList;
    }
}
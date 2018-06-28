package com.integrate.todo.db;

import com.integrate.todo.Item;
import com.integrate.todo.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteItem implements DBWrapperItem {
//
//    @Autowired
//    DataSource dataSource;
//
//
//    @Override
//    public TodoList createItem(Item item) {
//        String item = todoList.getTitle();
//        Integer todoListUserID = todoList.getUserID();
//        String todoListArchiveStatus = todoList.getArchiveStatus();
//        try {
//            Connection connection = dataSource.getConnection();
//            Statement statement = connection.createStatement();
//            statement.executeUpdate(
//                    "INSERT INTO List (USER_ID, LIST_NAME, ARCHIVE_STATUS) VALUES ('" + todoListUserID + "','" + todoListTitle + "','" + todoListArchiveStatus + "')"
//            );
//            statement.close();
//            statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid()");
//            int id = resultSet.getInt("last_insert_rowid()");
//            todoList.setListID(id);
//            connection.close();
//            return todoList;
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        todoList.setListID(-1);
//        return todoList;
//    }
}

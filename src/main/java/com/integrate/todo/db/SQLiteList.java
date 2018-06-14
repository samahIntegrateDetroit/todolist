package com.integrate.todo.db;

import com.integrate.todo.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class SQLiteList implements DBWrapper{

    @Autowired
    DataSource dataSource;


    @Override
    public TodoList createList(TodoList todoList) {
        String todoListTitle = todoList.getTitle();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO List (LIST_NAME) VALUES ('"+todoListTitle+"')"
            );
            statement.close();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid()");
            int id = resultSet.getInt("last_insert_rowid()");
            todoList.setListID(id);
            connection.close();
            return todoList;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        todoList.setListID(-1);
        return todoList;
    }

    @Override
    public TodoList findListById(int id) {
        TodoList todoList = new TodoList();

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery( "SELECT * from List WHERE ID=" + id + ";" );

            if( resultSet.next() == false ) {
                todoList.setListID( -1 );
            } else {
                todoList.setListID( resultSet.getInt( "ID" ) );
                todoList.setTitle( resultSet.getString( "LIST_NAME" ) );
            }
            connection.close();
            return todoList;
        } catch( SQLException e ) { e.printStackTrace(); }

        todoList.setListID( -1 );

        return todoList;
    }

    @Override
    public TodoList updateListTitle(int id, String newTitle) {
        TodoList todoList = new TodoList();

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "UPDATE List SET LIST_NAME = newTitle WHERE ID = id;"
            );
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        todoList.setListID(-1);
        return todoList;


    }
}

package com.integrate.todo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


@Component
public class DBUtils {

    @Autowired
    private DataSource dataSource;
    private Connection connection;
    private Statement statement;


    public void initialize(){
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        initializeListTable();
        initializeUserTable();

        try{
            statement.close();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeUserTable() {
        try {
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS User(" +
                            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "FIRST_NAME text NOT NULL, " +
                            "LAST_NAME text NOT NULL, " +
                            "EMAIL text NOT NULL UNIQUE , " +
                            "PASSWORD_HASH text NOT NULL , " +
                            "SIGNUP_DATE date DEFAULT current_timestamp  , " +
                            "PREFERENCE INTEGER DEFAULT 0)"
            );
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeListTable(){
        try {
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS List(" +
                            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "LIST_NAME varchar(30) not null)"
            );
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

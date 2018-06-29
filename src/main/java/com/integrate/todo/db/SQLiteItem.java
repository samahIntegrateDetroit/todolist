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

    @Autowired
    DataSource dataSource;


    @Override
    public Item createItem(Item item) {
        Integer listID = item.getListID();
        String description = item.getDescription();

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO Item (LIST_ID, DESCRIPTION) VALUES ('" + listID + "','" + description + "')"
            );
            statement.close();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid()");
            int id = resultSet.getInt("last_insert_rowid()");
            item.setItemID(id);
            connection.close();
            return item;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        item.setItemID(-1);
        return item;
    }
}

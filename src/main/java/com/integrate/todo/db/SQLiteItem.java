package com.integrate.todo.db;

import com.integrate.todo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class SQLiteItem implements DBWrapperItem{

    @Autowired
    DataSource dataSource;


    @Override
    public Item addItemToList(Item item, Integer listID) {
        String itemDescription = item.getDescription();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO Item (LIST_ID, DESCRIPTION) VALUES ('" + listID + "','" + itemDescription +  "')"
            );
            statement.close();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid()");
            int id = resultSet.getInt("last_insert_rowid()");
            item.setID(id);
            connection.close();
            return item;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        item.setID(-1);
        return item;
    }
}

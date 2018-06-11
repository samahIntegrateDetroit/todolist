package com.integrate.todo.db;

import com.integrate.todo.TodoList;
import com.integrate.todo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class SQLiteUser implements DBWrapperUser{

    @Autowired
    DataSource dataSource;


    @Override
    public User createUser(User user) {

        String firstName=user.getFirstName();
        String lastName=user.getLastName();
        String eMail=user.geteMail();
        String passwordHash=user.getPasswordHash();
        String signupDate=user.getSignupDate();
        int preference=user.getPreference();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO User (" +
                            "FIRST_NAME," +
                            "LAST_NAME," +
                            "EMAIL," +
                            "PASSWORD_HASH," +
                            "SIGNUP_DATE," +
                            "PREFERENCE) VALUES ('"+firstName+","+
                            lastName+","+
                            eMail+","+
                            passwordHash+","+
                            signupDate+","+
                            preference+"')"
            );
            statement.close();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid()");
            int id = resultSet.getInt("last_insert_rowid()");
            user.setUserId(id);
            connection.close();
            return user;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        user.setUserId(-1);
        return user;
    }

    @Override
    public User findUserById(int id) {
        User user = new User();

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery( "SELECT * from List WHERE ID=" + id + ";" );

            if( resultSet.next() == false ) {
                user.setUserId( -1 );
            } else {
                user.setUserId( resultSet.getInt( "ID" ) );
                user.setFirstName( resultSet.getString( "FIRST_NAME" ) );
                user.setLastName( resultSet.getString( "LAST_NAME" ) );
                user.seteMail( resultSet.getString( "EMAIL" ) );
                user.setPasswordHash( resultSet.getString( "PASSWORD_HASH" ) );
                user.setSignupDate( resultSet.getString( "SIGNUP_DATE" ) );
                user.setPreference( resultSet.getInt( "PREFERENCE" ) );

            }
            return user;
        } catch( SQLException e ) { e.printStackTrace(); }

        user.setUserId( -1 );

        return user;
    }

    public User findUserByEmail(String expectedEmail) {
        return null;
    }
}

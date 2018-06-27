package com.integrate.todo.db;


import com.integrate.todo.User;

import java.sql.SQLException;

public interface DBWrapperUser {

    User deleteUser(String email);

    User findUserById(int id);

    User createUser(User user);

    User findUserByEmail(String email);
}

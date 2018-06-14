package com.integrate.todo.db;


import com.integrate.todo.User;

public interface DBWrapperUser {

    User findUserById(int id);

    User createUser(User user);

    User findUserByEmail(String email);
}

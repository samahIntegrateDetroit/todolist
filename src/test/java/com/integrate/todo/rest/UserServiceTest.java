package com.integrate.todo.rest;
import com.integrate.todo.User;
import com.integrate.todo.db.SQLiteUser;
import com.integrate.todo.rest.UserService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {


    private SQLiteUser db;

    private UserService service;

    @Before
    public void setup() {

        this.db = mock(SQLiteUser.class);

        this.service = new UserService(this.db);

    }

    @Test
    public void createUser_savesToRepository() {
        int expectedID = 1;
        String expectedFirstName = "Zaara";
        String expectedLastName = "Userson";
        String expectedUserName = "Zaara0001";
        String expectedEmail = "zaara0001@example.com";
        String expectedPasswordHash = "password";
        String expectedSignupDate = "6/9/2018";
        int expectedPreference = 0;

        User inputUser = new User().setUser(expectedFirstName,expectedLastName,expectedUserName,expectedEmail,expectedPasswordHash, expectedSignupDate,expectedPreference);

        User expectedUser = new User()
                .setUser(expectedFirstName,expectedLastName,expectedUserName,expectedEmail,expectedPasswordHash, expectedSignupDate,expectedPreference);

        when(this.db.createUser(inputUser))
                .thenReturn(expectedUser);

        User user = this.service.createUser(
                new User().setUser(expectedFirstName,expectedLastName,expectedUserName,expectedEmail,expectedPasswordHash, expectedSignupDate,expectedPreference));


        verify(db)
                .createUser(inputUser);

        assertThat(user.getUserId())
                .isEqualTo(expectedUser.getUserId());
    }

    @Test
    public void getUser_returnsUserWithCorrectID() {
        int expectedUserID = 1;
        String expectedFirstName = "Zaara";
        String expectedLastName = "Userson";
        String expectedUserName = "Zaara0001";
        String expectedEmail = "zaara0001@example.com";
        String expectedPasswordHash = "password";
        String expectedSignupDate = "6/9/2018";
        int expectedPreference = 0;
        User expectedUser = new User().setUser(expectedFirstName,expectedLastName,expectedUserName,expectedEmail,expectedPasswordHash, expectedSignupDate,expectedPreference);


        when(this.db.findUserById(expectedUserID))
                .thenReturn(expectedUser);

        User user = this.service.getUser(expectedUserID);

        verify(this.db)
                .findUserById(expectedUserID);

        assertThat(expectedUser)
                .isEqualTo(user);
    }

}

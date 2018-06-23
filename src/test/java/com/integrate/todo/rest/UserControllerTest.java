package com.integrate.todo.rest;

import com.integrate.todo.User;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @Test
    public void createUser_returnsHttpStatusCreated() {
        UserService mockService = mock( UserService.class );
        UserController userController = new UserController(mockService);

        String testEmail = null;

        User expectedUser = new User();
        User userPassedIn = new User();

        User testUser = new User();
        testUser.setUserID(-1);

        when( mockService.createUser( userPassedIn ) )
                .thenReturn( expectedUser );

        when( mockService.getUserByEmail(testEmail) )
                .thenReturn( testUser );

        ResponseEntity expectedResponseEntity = new ResponseEntity<>(
                expectedUser, HttpStatus.CREATED);

        ResponseEntity<User> responseEntity = userController.createUser( userPassedIn );

        verify( mockService )
                .createUser( userPassedIn );

        assertThat( responseEntity )
                .isEqualTo( expectedResponseEntity );
    }

    @Test
    public void getUser_returnsUserAndHttpStatus200() {


        UserService mockService = mock( UserService.class );
        UserController userController = new UserController( mockService );

        User expectedUser = new User();
        expectedUser.setUserID(1);

        when( mockService.getUser( 1 ) )
                .thenReturn( expectedUser );

        ResponseEntity<User> resultUser = userController.readUser(1);
        ResponseEntity expectedResponse = new ResponseEntity<>( // compare what I an getting back to what I expect
                expectedUser,
                HttpStatus.OK
        );

        verify( mockService )
                .getUser( 1 );

        assertThat( resultUser )
                .isEqualTo( expectedResponse );
    }

    @Test
    public void getUserByEmail_returnsUserAndHttpStatus200() {


        UserService mockService = mock( UserService.class );
        UserController userController = new UserController( mockService );

        User expectedUser = new User();
        expectedUser.setUserID(2);
        expectedUser.setEmail("zaara0001@example.com");

        when( mockService.getUserByEmail("zaara0001@example.com") )
                .thenReturn( expectedUser );

        ResponseEntity<User> resultUser = userController.readUserByEmail("zaara0001@example.com");
        ResponseEntity expectedResponse = new ResponseEntity<>( // compare what I an getting back to what I expect
                expectedUser,
                HttpStatus.OK
        );

        verify( mockService )
                .getUserByEmail("zaara0001@example.com" );

        assertThat( resultUser )
                .isEqualTo( expectedResponse );
    }

    @Test
    public void getUser_whenDoesntExist_returnsHttpStatus204(){
        UserService mockService = mock( UserService.class );
        UserController userController = new UserController( mockService );

        User expectedUser = new User();
        expectedUser.setUserID( -1 );
        int input_id = 8;

        when( mockService.getUser( input_id ) )
                .thenReturn( expectedUser );

        ResponseEntity expectedResponse = new ResponseEntity<>(
                expectedUser,
                HttpStatus.NO_CONTENT
        );

        ResponseEntity<User> resultUser = userController.readUser( input_id );


        verify( mockService )
                .getUser( input_id );

        assertThat( resultUser )
                .isEqualTo( expectedResponse );
    }

    @Test
    public void createUser_returnsHttpStatusBadRequestIfUserAlreadyExists(){
        UserService mockService = mock( UserService.class );
        UserController userController = new UserController(mockService);

        String testEmail = "TestEmail";
        User expectedUser = new User();
        expectedUser.setUserID(2);
        expectedUser.setEmail(testEmail);

        User userPassedIn = new User();
        userPassedIn.setUserID(2);
        userPassedIn.setEmail(testEmail);

        when( mockService.getUserByEmail( testEmail ) )
                .thenReturn( expectedUser );

        ResponseEntity expectedResponseEntity = new ResponseEntity<>(
                expectedUser, HttpStatus.BAD_REQUEST);

        ResponseEntity<User> responseEntity = userController.createUser( userPassedIn );

        verify( mockService )
                .getUserByEmail( testEmail );

        assertThat( responseEntity )
                .isEqualTo( expectedResponseEntity );
    }
//
//    @Test
//    public void updateListTitle_updatesListTitle_returnsHttpStatus200() {
//        ListService mockService = mock( ListService.class );
//        ListController todoListController = new ListController(mockService);
//        String newTitle = "New Title";
//        int listID = 1;
//        TodoList todoList = new TodoList().setTitle(newTitle).setListID(listID);
//
//        when (mockService.updateList(listID, newTitle))
//                .thenReturn( todoList );
//
//
//        ResponseEntity<TodoList> responseEntity = todoListController.updateList(listID,newTitle);
//        TodoList body = responseEntity.getBody();
//
//        verify(mockService).updateList(listID, newTitle);
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(body.getTitle()).isEqualTo(newTitle);
//        assertThat(body.getListID()).isEqualTo(listID);
//
//    }
}
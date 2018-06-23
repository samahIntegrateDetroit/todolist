package com.integrate.todo.rest;

import com.integrate.todo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service ) {
        this.service = service;
    }

    @CrossOrigin(origins = "http://localhost:9876")
    @PostMapping
    public @ResponseBody
    ResponseEntity<User> createUser(@RequestBody User user) {
        //check if user already exists
        String userEmail = user.geteMail();
        User checkUser = this.service.getUserByEmail( userEmail );
        if(checkUser.getUserID() == -1 ) {
            return new ResponseEntity<>(
                    this.service.createUser(user),
                    HttpStatus.CREATED
            );
        }else{
            return new ResponseEntity<>(
                    user,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/id/{id}")
    public @ResponseBody
    ResponseEntity<User> readUser(@PathVariable Integer id ) {
        User user = this.service.getUser( id );
        if(user.getUserID() == -1 )
            return new ResponseEntity<>( user, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>( user, HttpStatus.OK );
    }

    @GetMapping("/email/{email}")
    public @ResponseBody
    ResponseEntity<User> readUserByEmail(@PathVariable String email ) {
        User user = this.service.getUserByEmail( email );
        if(user.getUserID() == -1 )
            return new ResponseEntity<>( user, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>( user, HttpStatus.OK );
    }

    @GetMapping("/deleteUser/{email}")
    public @ResponseBody
    ResponseEntity<User> deleteUserByEmail(@PathVariable String email ) {
        User user = this.service.getUserByEmail( email );
        if(user.getUserID() == -1 ) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }else{

            int deleteResult = this.service.deleteUser(email);
            if (deleteResult==1) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

}


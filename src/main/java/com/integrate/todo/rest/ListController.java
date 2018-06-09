package com.integrate.todo.rest;

import com.integrate.todo.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSException;

@RestController
@RequestMapping("/list")
public class ListController {
        private final ListService service;

        @Autowired
        public ListController(ListService service ) {
            this.service = service;
        }

        @CrossOrigin(origins = "http://localhost:9876")
        @PostMapping
        public @ResponseBody
        ResponseEntity<TodoList> createList(@RequestBody TodoList todoList) {
            return new ResponseEntity<>(
                    this.service.createTodoList( todoList ),
                    HttpStatus.CREATED
            );
        }

        @GetMapping("/{id}")
        public @ResponseBody
        ResponseEntity<TodoList> readList( @PathVariable Integer id ) {
            TodoList list = this.service.getList( id );
            if(list.getListID() == -1 )
                return new ResponseEntity<>( list, HttpStatus.NO_CONTENT);
            return new ResponseEntity<>( list, HttpStatus.OK );
        }

        @PutMapping
        public @ResponseBody
        ResponseEntity<TodoList> updateList(@RequestBody int id, @RequestBody String updatedTitle) {
            return new ResponseEntity<>(this.service.updateList(id, updatedTitle), HttpStatus.OK);
        }

        @DeleteMapping
        public void deleteList() {

        }


}

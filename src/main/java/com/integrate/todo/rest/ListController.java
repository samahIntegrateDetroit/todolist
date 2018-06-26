package com.integrate.todo.rest;

import com.integrate.todo.Item;
import com.integrate.todo.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSException;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/list")
public class ListController {
    private final ListService service;

    @Autowired
    public ListController(ListService service ) {
        this.service = service;
    }

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

    @PutMapping("/{id}")
    public @ResponseBody
    ResponseEntity<TodoList> updateList(
        @PathVariable Integer id,
        @RequestBody Map<String, Object> newTitle
    ) {
        TodoList list = this.service.getList( id );
        if(list.getListID() == -1 )
            return new ResponseEntity<>( list, HttpStatus.NOT_MODIFIED );
        return new ResponseEntity<>(this.service.updateList(id, newTitle.get("updatedTitle").toString()), HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteList() {

    }

    @PutMapping("archive/{listID}")
    public @ResponseBody
    ResponseEntity<TodoList> archiveList(@PathVariable Integer listID) {
        TodoList list = this.service.getList(listID);
        if(list.getListID() == -1 )
            return new ResponseEntity<>( list, HttpStatus.NOT_MODIFIED);
        return new ResponseEntity<>(this.service.archiveList(list), HttpStatus.OK);
    }

    @PostMapping("/{listID}")
    public @ResponseBody ResponseEntity<Item> createItem(@RequestBody Item item, @PathVariable Integer listID) {
        Item newItem = this.service.createItem(item, listID);
        if (newItem.getID() == -1){
            return new ResponseEntity<>(newItem, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }
}

package com.integrate.todo.rest;

import com.integrate.todo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/list/item")
public class ItemController {
    private final ItemService service;

    @Autowired
    public ItemController(ItemService service ) {
        this.service = service;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<Item> createItem(@RequestBody Item item) {
        item = this.service.createItem( item );
        if (item.getItemID() == -1) {
           return new ResponseEntity<>(item, HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(
               item, HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Item> readItem(@PathVariable Integer id ) {
        Item list = this.service.getItem( id );
        if(list.getItemID() == -1 )
            return new ResponseEntity<>( list, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>( list, HttpStatus.OK );
    }

    @PutMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Item> updateItem(
            //@PathVariable Integer id,
            @RequestBody Map<String, Object> newItem
    ) {
        Item inputItem = new Item();
        inputItem.setItemID((int)newItem.get("itemID"));
        inputItem.setListID((int)newItem.get("newListID"));
        inputItem.setDescription((String)newItem.get("newDescription"));

        Item updatedItem = this.service.updateItem(inputItem);
        if(updatedItem.getItemID() == -1 ){
            return new ResponseEntity<>( updatedItem, HttpStatus.NOT_MODIFIED );}

        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }
}

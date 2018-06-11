package com.integrate.todo.rest;

import com.integrate.todo.FolderList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


    @RestController
    @RequestMapping("/folder")
    public class FolderController {
        private FolderService service;

        @Autowired
        public FolderController(FolderService service) { this.service = service; }

        @CrossOrigin(origins = "http://localhost:9876")
        @PostMapping
        public @ResponseBody
        ResponseEntity<FolderList> createFolderList(@RequestBody FolderList folderList) {
            return new ResponseEntity<>(
                    this.service.createFolderList(folderList),
                    HttpStatus.CREATED
            );
        }

    }


package com.example.demo.controller;

import com.example.demo.dto.PersonDTO;
import com.example.demo.service.CreatePersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/persons")
public class PersonController {
    private final CreatePersonService createPersonService;

    public PersonController(CreatePersonService createPersonService) {
        this.createPersonService = createPersonService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody PersonDTO personDTO) {
        try {
            String personId = createPersonService.create(personDTO);
            return ResponseEntity.ok("Successfully created a New Person with id:" + " " + personId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

}



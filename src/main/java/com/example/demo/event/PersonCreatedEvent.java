package com.example.demo.event;

import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class PersonCreatedEvent {

     UUID personId;
     String lastname;
     String firstname;
     String gender;
     LocalDate dateOfBirth;




}

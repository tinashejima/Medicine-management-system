package com.example.demo.dto;

import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class PersonDTO {


     UUID personId;

     String lastname;

     String firstname;

     LocalDate dateOfBirth;

     String gender;
}


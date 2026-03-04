package com.example.demo.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class CreatePersonCommand {

    @TargetAggregateIdentifier
     UUID personId;
     String lastname;
     String firstname;
     String Gender ;
     LocalDate dateOfBirth;

}


package com.example.demo.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

@Value
public class CreateMedicineCommand {

    @TargetAggregateIdentifier
    String medicineId;
    String name;
    String dosage;
    String manufacturer;
    LocalDate expirationDate;
    int quantity;



}

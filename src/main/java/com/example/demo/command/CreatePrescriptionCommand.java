package com.example.demo.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

@Value
public class CreatePrescriptionCommand {

    @TargetAggregateIdentifier
    String prescriptionId;
    String personId;
    String medicineId;
    String dosage;
    String instructions;
    LocalDate dateIssued;

}

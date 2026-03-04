package com.example.demo.event;

import lombok.Value;

import java.time.LocalDate;
@Value
public class PrescriptionCreatedEvent {
    String prescriptionId;
    String personId;
    String medicineId;
    String dosage;
    String instructions;
    LocalDate dateIssued;
}

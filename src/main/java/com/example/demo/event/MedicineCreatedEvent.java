package com.example.demo.event;

import lombok.Value;

import java.time.LocalDate;

@Value
public class MedicineCreatedEvent {

    String medicineId;
    String name;
    String dosage;
    String manufacturer;
    LocalDate expirationDate;
    int quantity;
}

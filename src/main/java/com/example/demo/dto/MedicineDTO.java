package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicineDTO {

    String name;
    String dosage;
    String manufacturer;
    LocalDate expirationDate;
    int quantity;


}

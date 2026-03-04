package com.example.demo.dto;


import lombok.Value;

import java.time.LocalDate;

@Value
public class PrescriptionDTO {
    String personId;
    String medicineId;
    String dosage;
    String instructions;
    LocalDate dateIssued;


}

package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
    private String prescriptionId;

    //  One person can have many prescriptions
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId", nullable = false)
    private Person person;

    //  Many prescriptions can reference one medicine
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicineId", nullable = false)
    private Medicine medicine;

    @NotNull
    private String dosage;

    @NotNull
    private String instructions;

    @NotNull
    private LocalDate dateIssued;
}

package com.example.demo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Medicine {
    @Id
    private String medicineId;

    @NotNull
    private String name;

    @NotNull
    private String dosage;

    @NotNull
    private String manufacturer;

    @NotNull
    private LocalDate expirationDate;

    @NotNull
    private int quantity;

}

package com.example.demo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
@Entity
@Table (name = "person",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"firstname", "lastname", "dateOfBirth"}) // This Combination must be unique
        }
)
public class Person {

    @Id
    private String personId;

    @NotNull
    private String lastname;

    @NotNull
    private String firstname;

    @NotNull
    private  String gender;

    @NotNull
    private LocalDate dateOfBirth;
}

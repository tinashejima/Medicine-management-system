package com.example.demo.repository;

import com.example.demo.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PersonRepository extends JpaRepository<Person, String> {

    boolean existsByPersonId(String personId);

    boolean existsByFirstnameAndLastnameAndDateOfBirth(String firstname, String lastname, LocalDate dateOfBirth);
}

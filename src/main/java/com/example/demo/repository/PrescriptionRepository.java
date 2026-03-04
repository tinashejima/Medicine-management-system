package com.example.demo.repository;

import com.example.demo.domain.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

import java.util.UUID;

public interface PrescriptionRepository extends JpaRepository <Prescription, String>{



    boolean existsByPerson_PersonIdAndMedicine_MedicineIdAndDateIssued(String person_personId, String medicine_medicineId, LocalDate dateIssued);
}

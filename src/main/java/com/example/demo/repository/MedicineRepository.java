package com.example.demo.repository;

import com.example.demo.domain.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, String> {
    boolean existsByNameAndDosage(String name, String dosage);
}

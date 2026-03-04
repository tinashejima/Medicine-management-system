package com.example.demo.util;

import com.example.demo.repository.MedicineRepository;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MedicineIdGenerator {

    private final MedicineRepository medicineRepository;

    public MedicineIdGenerator(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public String generateId() {
        long count = medicineRepository.count() + 1;
        String prefix = generateRandomPrefix();
        return String.format("%s-%03d", prefix, count);
    }

    private String generateRandomPrefix() {
        Random random = new Random();
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            prefix.append((char) ('A' + random.nextInt(26)));
        }
        return prefix.toString();
    }



}

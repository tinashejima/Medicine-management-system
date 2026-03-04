package com.example.demo.controller;

import com.example.demo.dto.MedicineDTO;
import com.example.demo.service.CreateMedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/medicines")
public class MedicineController {

    private final CreateMedicineService createMedicineService;

    public MedicineController(CreateMedicineService createMedicineService) {
        this.createMedicineService = createMedicineService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createMedicine(@RequestBody MedicineDTO medicineDTO) {
        try {
            String medicineId = createMedicineService.createMedicine(medicineDTO);
            return ResponseEntity.ok("Successfully added new medicine" + " " + medicineId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

}

}

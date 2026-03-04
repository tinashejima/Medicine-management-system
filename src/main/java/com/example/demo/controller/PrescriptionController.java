package com.example.demo.controller;

import com.example.demo.dto.PrescriptionDTO;
import com.example.demo.service.CreatePrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/prescriptions")
public class PrescriptionController {

    private final CreatePrescriptionService createPrescriptionService;

    public PrescriptionController(CreatePrescriptionService createPrescriptionService) {
        this.createPrescriptionService = createPrescriptionService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        try {
            String prescriptionId = createPrescriptionService.createPrescription(prescriptionDTO);
            return ResponseEntity.ok("Successfully prescribed a patient with" + " " + prescriptionId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}

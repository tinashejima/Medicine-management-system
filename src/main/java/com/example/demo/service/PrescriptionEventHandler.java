package com.example.demo.service;

import com.example.demo.domain.Prescription;
import com.example.demo.event.PrescriptionCreatedEvent;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.PrescriptionRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PrescriptionEventHandler {
    private final PrescriptionRepository prescriptionRepository;
    private final PersonRepository personRepository;
    private final MedicineRepository medicineRepository;


    public PrescriptionEventHandler(PrescriptionRepository prescriptionRepository, PersonRepository personRepository,
                                    MedicineRepository medicineRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.personRepository = personRepository;
        this.medicineRepository = medicineRepository;
    }

    @EventHandler
    public void onPrescriptionCreated(PrescriptionCreatedEvent event){

        Prescription prescription = new Prescription(
                event.getPrescriptionId(),
                personRepository.findById(String.valueOf(UUID.fromString(event.getPersonId())))
                        .orElseThrow(() -> new RuntimeException("Person not found")),
                medicineRepository.findById(event.getMedicineId())
                        .orElseThrow(() -> new RuntimeException("Medicine not found")),
                event.getDosage(),
                event.getInstructions(),
                event.getDateIssued()
        );
        prescriptionRepository.save(prescription);
    }
}

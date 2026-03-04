package com.example.demo.service;

import com.example.demo.command.CreatePrescriptionCommand;
import com.example.demo.dto.PrescriptionDTO;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.PrescriptionRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CreatePrescriptionService {
    private final CommandGateway commandGateway;
    private final PersonRepository personRepository;
    private final MedicineRepository medicineRepository;
    private final PrescriptionRepository prescriptionRepository;

    public CreatePrescriptionService(CommandGateway commandGateway, PersonRepository personRepository,
                                     MedicineRepository medicineRepository, PrescriptionRepository prescriptionRepository) {
        this.commandGateway = commandGateway;
        this.personRepository = personRepository;
        this.medicineRepository = medicineRepository;
        this.prescriptionRepository = prescriptionRepository;
    }


    public String createPrescription(PrescriptionDTO dto) {
        //Check empty fields
        if (dto.getPersonId() == null || dto.getPersonId().isBlank()) {
            throw new IllegalArgumentException("Person ID cannot be empty");
        }
        if (dto.getMedicineId() == null || dto.getMedicineId().isBlank()) {
            throw new IllegalArgumentException("Medicine ID cannot be empty");
        }
        if (dto.getDosage() == null || dto.getDosage().isBlank()) {
            throw new IllegalArgumentException("Dosage cannot be empty");
        }
        if (dto.getInstructions() == null || dto.getInstructions().isBlank()) {
            throw new IllegalArgumentException("Instructions cannot be empty");
        }
        if (dto.getDateIssued() == null) {
            throw new IllegalArgumentException("Date issued cannot be empty");
        }

        //  Date issued cannot be in the future
        if (dto.getDateIssued().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date issued cannot be in the future");
        }

        // Check if the person exists
        UUID personUUID = UUID.fromString(dto.getPersonId());
        if (!personRepository.existsById(String.valueOf(personUUID))) {
            throw new IllegalArgumentException(
                    "Person with ID " + dto.getPersonId() + " does not exist"
            );
        }

        //  Medicine must exist
        if (!medicineRepository.existsById(dto.getMedicineId())) {
            throw new IllegalArgumentException(
                    "Medicine with ID " + dto.getMedicineId() + " does not exist"
            );
        }

        //  Medicine must not be expired
        medicineRepository.findById(dto.getMedicineId()).ifPresent(medicine -> {
            if (medicine.getExpirationDate().isBefore(LocalDate.now())) {
                throw new IllegalArgumentException(
                        "Medicine " + medicine.getName() + " has expired"
                );
            }
        });

        //  No duplicate prescription (same person, medicine, date)
        if (prescriptionRepository.existsByPerson_PersonIdAndMedicine_MedicineIdAndDateIssued(
                String.valueOf(personUUID), dto.getMedicineId(), dto.getDateIssued())) {
            throw new IllegalArgumentException(
                    "This prescription already exists for this person on this date"
            );
        }

        String prescriptionId = "PRE-" + String.format("%03d",
                prescriptionRepository.count() + 1);

        commandGateway.sendAndWait(new CreatePrescriptionCommand(
                prescriptionId,
                dto.getPersonId(),
                dto.getMedicineId(),
                dto.getDosage(),
                dto.getInstructions(),
                dto.getDateIssued()
        ));

        return prescriptionId;
    }
}




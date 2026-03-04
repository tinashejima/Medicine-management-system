package com.example.demo.service;

import com.example.demo.command.CreateMedicineCommand;
import com.example.demo.dto.MedicineDTO;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.util.MedicineIdGenerator;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
public class CreateMedicineService {

    private final CommandGateway commandGateway;
    private final MedicineIdGenerator medicineIdGenerator;
    private final MedicineRepository medicineRepository;

    public CreateMedicineService(CommandGateway commandGateway, MedicineIdGenerator medicineIdGenerator, MedicineRepository medicineRepository) {
        this.commandGateway = commandGateway;
        this.medicineIdGenerator = medicineIdGenerator;
        this.medicineRepository = medicineRepository;
    }


    public String createMedicine(MedicineDTO dto) {
        String medicineId = medicineIdGenerator.generateId();

        // VALIDATION

        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Medicine name cannot be empty");
        }
        if (dto.getDosage() == null || dto.getDosage().isBlank()) {
            throw new IllegalArgumentException("Dosage cannot be empty");
        }
        if (dto.getManufacturer() == null || dto.getManufacturer().isBlank()) {
            throw new IllegalArgumentException("Manufacturer cannot be empty");
        }
        if (dto.getExpirationDate() == null) {
            throw new IllegalArgumentException("Expiration date cannot be empty");
        }

        //   Expiration date must be in the future
        if (dto.getExpirationDate().isBefore(java.time.LocalDate.now())) {
            throw new IllegalArgumentException("Expiration date must be in the future");
        }

        // Quantity must be positive
        if (dto.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        //  Check duplicate medicine name + dosage combination
        if (medicineRepository.existsByNameAndDosage(dto.getName(), dto.getDosage())) {
            throw new IllegalArgumentException("Medicine with this name and dosage already exists");
        }

        commandGateway.send(new CreateMedicineCommand(medicineId,
                dto.getName(),
                dto.getDosage(),
                dto.getManufacturer(),
                dto.getExpirationDate(),
                dto.getQuantity()));

        return medicineId;

    }
}

package com.example.demo.service;


import com.example.demo.domain.Medicine;
import com.example.demo.event.MedicineCreatedEvent;
import com.example.demo.repository.MedicineRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class MedicineEventHandler {


    private final MedicineRepository medicineRepository;

    public MedicineEventHandler(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @EventHandler
    public void onMedicineCreated(MedicineCreatedEvent event) {
        Medicine medicine = new Medicine(
                event.getMedicineId(),
                event.getName(),
                event.getDosage(),
                event.getManufacturer(),
                event.getExpirationDate(),
                event.getQuantity()
        );
        medicineRepository.save(medicine);
    }

}

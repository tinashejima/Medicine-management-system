package com.example.demo.aggregate;

import com.example.demo.command.CreateMedicineCommand;
import com.example.demo.event.MedicineCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
public class MedicineAggregate {

    @AggregateIdentifier
    private String medicineId;


    @CommandHandler
    public MedicineAggregate(CreateMedicineCommand medicineCommand) {
        MedicineCreatedEvent medicineCreatedEvent = new MedicineCreatedEvent(medicineCommand.getMedicineId(), medicineCommand.getName(),
                medicineCommand.getDosage(), medicineCommand.getManufacturer(), medicineCommand.getExpirationDate(), medicineCommand.getQuantity());

        apply(medicineCreatedEvent);

    }

    @EventSourcingHandler
    public void on(MedicineCreatedEvent event) {
        this.medicineId = event.getMedicineId();
    }

    public MedicineAggregate(){

    }

}




package com.example.demo.aggregate;

import com.example.demo.command.CreatePrescriptionCommand;
import com.example.demo.event.PrescriptionCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
public class PrescriptionAggregate {

    @AggregateIdentifier
    private String prescriptionId;


    @CommandHandler
    public PrescriptionAggregate(CreatePrescriptionCommand command){
        apply(new PrescriptionCreatedEvent(
                command.getPrescriptionId(),
                command.getPersonId(),
                command.getMedicineId(),
                command.getDosage(),
                command.getInstructions(),
                command.getDateIssued()
        ));
    }

    @EventSourcingHandler
    public void on(PrescriptionCreatedEvent event){
        this.prescriptionId = event.getPrescriptionId();
    }}

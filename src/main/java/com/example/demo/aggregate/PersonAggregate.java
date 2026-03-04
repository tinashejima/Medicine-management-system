package com.example.demo.aggregate;

import com.example.demo.command.CreatePersonCommand;
import com.example.demo.event.PersonCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
public class PersonAggregate {

    @AggregateIdentifier
    private UUID personId;


    @CommandHandler
    public PersonAggregate(CreatePersonCommand command){
        PersonCreatedEvent event = new PersonCreatedEvent(
                command.getPersonId(),
                command.getLastname(),
                command.getFirstname(),
                command.getGender(),
                command.getDateOfBirth());

        apply(event);
    }

    @EventSourcingHandler
    public void on(PersonCreatedEvent event) {
        this.personId = event.getPersonId();
    }

    public PersonAggregate(){

    }
}



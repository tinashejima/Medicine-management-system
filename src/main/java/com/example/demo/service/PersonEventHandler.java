package com.example.demo.service;

import com.example.demo.domain.Person;
import com.example.demo.event.PersonCreatedEvent;
import com.example.demo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PersonEventHandler {


    private final PersonRepository personRepository;

    @Transactional
    @EventHandler
    public void onPersonCreated(PersonCreatedEvent event) {
        Person person = new Person();
        person.setPersonId(event.getPersonId().toString());
        person.setLastname(event.getLastname());
        person.setFirstname(event.getFirstname());
        person.setDateOfBirth(event.getDateOfBirth());
        person.setGender(event.getGender());
        personRepository.save(person);


    }

}


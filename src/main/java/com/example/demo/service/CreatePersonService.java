package com.example.demo.service;

import com.example.demo.command.CreatePersonCommand;
import com.example.demo.dto.PersonDTO;
import com.example.demo.repository.PersonRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CreatePersonService {

    private final CommandGateway commandGateway;
    private PersonRepository personRepository = null;

    public CreatePersonService(CommandGateway commandGateway,
                               PersonRepository personRepository){

        this.commandGateway = commandGateway;
        this.personRepository = personRepository;
    }

    public void create(UUID personId, String lastname, String firstname, String gender, LocalDate dateOfBirth){

    }

    public String create(PersonDTO personDTO) {
        // Check empty fields

        if (personDTO.getFirstname() == null || personDTO.getFirstname().isBlank()) {
            throw new IllegalArgumentException("Firstname cannot be empty");
        }

        if (personDTO.getLastname() == null || personDTO.getLastname().isBlank()) {
            throw new IllegalArgumentException("Lastname cannot be empty");
        }

        if (personDTO.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Date of birth cannot be empty");
        }

        if (personDTO.getGender() == null || personDTO.getGender().isBlank()) {
            throw new IllegalArgumentException("Gender cannot be empty");
        }

        // Date of birth must be current or in the past
        if (personDTO.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth must be in the past");
        }

        // Age must be reasonable (between 0 and 150)
        int age = LocalDate.now().getYear() - personDTO.getDateOfBirth().getYear();
        if (age > 150) {
            throw new IllegalArgumentException("Invalid date of birth");
        }

        // Check duplicate by firstname + lastname + dateOfBirth
        if (personRepository.existsByFirstnameAndLastnameAndDateOfBirth(
                personDTO.getFirstname(), personDTO.getLastname(), personDTO.getDateOfBirth())) {
            throw new IllegalArgumentException(
                    "A person with the same name and date of birth already exists"
            );
        }

        UUID personId;

        do {
            personId = UUID.randomUUID();
        }while (personRepository.existsByPersonId(String.valueOf(personId)));
        CreatePersonCommand command = new CreatePersonCommand(
                personId,
                personDTO.getLastname(),
                personDTO.getFirstname(),
                personDTO.getGender(),
                personDTO.getDateOfBirth());
        commandGateway.send(command);

        return  personId.toString();
    }
}

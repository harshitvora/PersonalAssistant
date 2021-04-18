package com.harshit.demo.dao;

import com.harshit.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    Optional<Person> getPersonById(UUID id);

    List<Person> getPersons();

    int updatePersonById(UUID id, Person person);

    int deletePersonById(UUID id);
}

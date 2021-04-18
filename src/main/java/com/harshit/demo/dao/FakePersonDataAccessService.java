package com.harshit.demo.dao;

import com.harshit.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Person> getPersons() {
        return DB;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return getPersonById(id).map(p -> {
            int indexofPerson = DB.indexOf(p);
            if (indexofPerson >= 0) {
                DB.set(indexofPerson, new Person(id, person.getName()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = getPersonById(id);
        if (personMaybe.isPresent()) {
            DB.remove(personMaybe.get());
            return 1;
        }
        return 0;
    }


}

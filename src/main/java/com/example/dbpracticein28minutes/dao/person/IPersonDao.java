package com.example.dbpracticein28minutes.dao.person;

import com.example.dbpracticein28minutes.model.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonDao {

    Optional<Person> findById(int id);

    Person findByNameAndLocation(String name, String location);

    List<Person> findAll();

    void save(Person person);

    void update(Person person);

    void deleteById(int id);

}

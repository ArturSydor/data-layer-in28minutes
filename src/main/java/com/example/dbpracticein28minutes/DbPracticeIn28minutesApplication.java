package com.example.dbpracticein28minutes;

import com.example.dbpracticein28minutes.dao.person.IPersonDao;
import com.example.dbpracticein28minutes.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class DbPracticeIn28minutesApplication implements CommandLineRunner {

    private final IPersonDao personDao;

    public static void main(String[] args) {
        SpringApplication.run(DbPracticeIn28minutesApplication.class, args);
    }

    @Override
    public void run(String... args) {
        displayAllPeople();
        log.info("Person found by its ID = {}", personDao.findById(10002)
                .orElseThrow(() -> new RuntimeException("No person was found by id")));
        log.info("Person found by its Name and Location = {}", personDao.findByNameAndLocation("Pieter", "Amsterdam"));
        log.info("{} people were deleted", personDao.deleteById(10003));
        displayAllPeople();
        Person person = new Person(10005, "Test", "Test town", new Date(System.currentTimeMillis()));
        log.info("Saving new person");
        personDao.save(person);
        displayAllPeople();
        log.info("Updating person name");
        person.setName("Omar");
        personDao.update(person);
        displayAllPeople();
    }

    private void displayAllPeople() {
        log.info("All people in DB");
        personDao.findAll().forEach(person -> log.info(person.toString()));
    }
}

package com.example.dbpracticein28minutes.dao.person;


import com.example.dbpracticein28minutes.model.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PersonJpaDao implements IPersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Person> findById(int id) {
        return Optional.ofNullable(entityManager.find(Person.class, id));
    }

    @Override
    public Person findByNameAndLocation(String name, String location) {
        return entityManager
                .createNamedQuery("findByNameAndLocation", Person.class)
                .setParameter("name", name)
                .setParameter("location", location)
                .getSingleResult();
    }

    @Override
    public List<Person> findAll() {
        return entityManager
                .createNamedQuery("findAll", Person.class)
                .getResultList();
    }

    @Override
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Override
    public void update(Person person) {
        entityManager.merge(person);
    }

    @Override
    public void deleteById(int id) {
        Person proxy = entityManager.getReference(Person.class, id);
        entityManager.remove(proxy);
    }
}

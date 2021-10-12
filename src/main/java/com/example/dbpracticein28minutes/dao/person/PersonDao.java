package com.example.dbpracticein28minutes.dao.person;

import com.example.dbpracticein28minutes.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonDao implements IPersonDao {

    private static final String FIND_ALL = "select * from person";

    private static final String FIND_BY_ID = "select * from person where id=?";

    private static final String FIND_BY_NAME_AND_LOCATION = "select * from person where name=? and location=?";

    private static final String DELETE_BY_ID = "delete from person where id=?";

    private static final String INSERT = "insert into person(id, name, location, birth_date) values(?, ?, ?, ?)";

    private static final String UPDATE = "update person set name = ?, location = ?, birth_date = ? where id = ?";

    private static final BeanPropertyRowMapper<Person> defaultMapper = new BeanPropertyRowMapper<>(Person.class);

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Person> findById(int id) {
        return jdbcTemplate.query(FIND_BY_ID, this::mapRow, id);
    }

    @Override
    public Person findByNameAndLocation(String name, String location) {
        return jdbcTemplate.queryForObject(FIND_BY_NAME_AND_LOCATION, defaultMapper, name, location);
    }

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query(FIND_ALL, defaultMapper);
    }

    @Override
    public int save(Person person) {
        return jdbcTemplate.update(INSERT, person.getId(), person.getName(),
                person.getLocation(), person.getBirthDate());
    }

    @Override
    public int update(Person person) {
        return jdbcTemplate.update(UPDATE, person.getName(),
                person.getLocation(), person.getBirthDate(), person.getId());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update(DELETE_BY_ID, id);
    }

    private Optional<Person> mapRow(ResultSet rs) throws SQLException {
        return rs.next() ?
                Optional.of(new Person(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        new Date(rs.getTimestamp("birth_date").getTime())))
                : Optional.empty();
    }

}

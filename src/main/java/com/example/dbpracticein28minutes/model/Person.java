package com.example.dbpracticein28minutes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "findByNameAndLocation",
                query = "select p from Person p where p.name=:name and p.location=:location"),
        @NamedQuery(name = "findAll", query = "select p from Person p")
})
public class Person {

    @Id
    private Integer id;
    private String name;
    private String location;
    private Date birthDate;

}

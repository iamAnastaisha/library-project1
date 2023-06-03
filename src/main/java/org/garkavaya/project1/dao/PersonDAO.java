package org.garkavaya.project1.dao;

import org.garkavaya.project1.models.Book;
import org.garkavaya.project1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonRowMapper());
    }

    public Map.Entry<Person, List<Book>> show(int id) {
        Person person = jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?",
                new Object[]{id}, new PersonRowMapper()).stream().findAny().orElse(null);
        List<Book> books = jdbcTemplate.query("SELECT book_id, Book.person_id, Book.name, Book.author, Book.year FROM Person JOIN Book ON Person.person_id = Book.person_id WHERE Person.person_id=?",
                new Object[]{id}, new BookRowMapper());
        return new Map.Entry<>() {
            @Override
            public Person getKey() {
                return person;
            }
            @Override
            public List<Book> getValue() {
                return books;
            }
            @Override
            public List<Book> setValue(List<Book> value) {
                return null;
            }
        };
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, year_of_birth) VALUES(?, ?)", person.getName(),
                person.getYearOfBirth());
    }

    public void update(int id, Person updatedPperson) {
        jdbcTemplate.update("UPDATE Person SET name = ?, year_of_birth = ? WHERE person_id=?", updatedPperson.getName(),
                updatedPperson.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }

    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?",
                new Object[]{name}, new PersonRowMapper()).stream().findAny();
    }
}

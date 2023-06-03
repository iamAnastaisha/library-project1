package org.garkavaya.project1.dao;

import org.garkavaya.project1.models.Book;
import org.garkavaya.project1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookRowMapper());
    }

    public Map.Entry<Book, Person> show(int id) {
        Book book = jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?",
                new Object[]{id}, new BookRowMapper()).stream().findAny().orElse(null);
        Person person = jdbcTemplate.query("SELECT Person.person_id, Person.name, Person.year_of_birth FROM Person JOIN Book ON Person.person_id = Book.person_id WHERE Book.book_id=?",
                new Object[]{id}, new PersonRowMapper()).stream().findAny().orElse(null);
        return new Map.Entry<>() {
            @Override
            public Book getKey() {
                return book;
            }
            @Override
            public Person getValue() {
                return person;
            }
            @Override
            public Person setValue(Person value) {
                return null;
            }
        };
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES(?, ?, ?)", book.getName(),
                book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name = ?, author = ?, year = ? WHERE book_id=?", updatedBook.getName(),
                updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public void appoint(int id, int person_id) {
        if (person_id == 0) {
            jdbcTemplate.update("UPDATE Book SET person_id = NULL WHERE book_id = ?", id);
        } else {
            jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE book_id = ?", person_id, id);
        }
    }
}

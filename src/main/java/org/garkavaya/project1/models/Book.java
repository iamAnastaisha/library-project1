package org.garkavaya.project1.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int bookId;
    private int personId;
    @NotEmpty(message = "Book's name shouldn't be empty")
    private String name;
    @NotEmpty(message = "Author's name shouldn't be empty")
    @Size(min = 2, max = 70, message = "Author's name should be between 2 and 70 characters")
    private String author;
    @Min(value = 1800, message = "Year of book should be greater then 1800")
    @Max(value = 2023, message = "Year of book should be lower then 2023")
    private int year;

    public Book() {
    }

    public Book(int bookId, int personId, String name, String author, int year) {
        this.bookId = bookId;
        this.personId = personId;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

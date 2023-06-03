package org.garkavaya.project1.models;

import jakarta.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 70, message = "Name should be between 2 and 70 characters")
    private String name;
    @Min(value = 1910, message = "Year of birth should be greater then 1910")
    @Max(value = 2023, message = "Year of birth should be lower then 2023")
    private int yearOfBirth;

    public Person() {}
    public Person(int id, String name, int yearOfBirth, String email, String address) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}

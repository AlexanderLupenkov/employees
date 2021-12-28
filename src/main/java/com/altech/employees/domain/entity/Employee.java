package com.altech.employees.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {

    private Integer id;

    private String firstName = "";

    private String lastName = "";

    private String email = "";

    private Organization organization;

    private Status status;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

package com.altech.employees.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Employee {
    private Integer id;

    @NotEmpty
    private String firstName = "";

    @NotEmpty
    private String lastName = "";

    @Email
    @NotEmpty
    private String email = "";

    @NotNull
    @JsonIgnoreProperties({"employees"})
    private Organization organization;

    @NotNull
    private Status status;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

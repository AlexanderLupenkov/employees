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

    @NotNull
    @JsonIgnoreProperties({"employees"})
    private Organization organization;

    @NotNull
    private Status status;

    @Email
    @NotEmpty
    private String email = "";

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

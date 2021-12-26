package com.altech.employees.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Organization {
    @NotBlank
    private String name;

    private List<Employee> employees = new LinkedList<>();
}

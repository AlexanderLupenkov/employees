package com.altech.employees.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class Organization {
    private Integer id;

    private String name;
}

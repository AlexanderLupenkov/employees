package com.altech.employees.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    private Integer id;
    private String name;

    public Status(String name) {
        this.name = name;
    }
}

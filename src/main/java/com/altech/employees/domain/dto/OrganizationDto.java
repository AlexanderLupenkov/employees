package com.altech.employees.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrganizationDto {

    private Integer id;

    @NotBlank
    private String name;
}

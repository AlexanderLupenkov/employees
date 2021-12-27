package com.altech.employees.domain.mapper;

import com.altech.employees.domain.dto.OrganizationDto;
import com.altech.employees.domain.dto.StatusDto;
import com.altech.employees.domain.entity.Organization;
import com.altech.employees.domain.entity.Status;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrganizationMapper {
    private final ModelMapper mapper;

    public OrganizationDto toDto(Organization org) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return mapper.map(org, OrganizationDto.class);
    }

    public Organization toEntity(OrganizationDto organizationDto) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return mapper.map(organizationDto, Organization.class);
    }

    public List<OrganizationDto> toDtoList(Iterable<Organization> organizations) {
        return ((List<Organization>) organizations).stream().map(this::toDto).collect(Collectors.toList());
    }
}

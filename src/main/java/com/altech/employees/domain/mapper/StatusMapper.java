package com.altech.employees.domain.mapper;

import com.altech.employees.domain.dto.StatusDto;
import com.altech.employees.domain.entity.Status;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class StatusMapper {
    private final ModelMapper mapper;

    public StatusDto toDto(Status status) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return mapper.map(status, StatusDto.class);
    }

    public Status toEntity(StatusDto statusDto) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return mapper.map(statusDto, Status.class);
    }

    public List<StatusDto> toDtoList(Iterable<Status> products) {
        return ((List<Status>) products).stream().map(this::toDto).collect(Collectors.toList());
    }
}

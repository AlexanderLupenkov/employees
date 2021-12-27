package com.altech.employees.domain.mapper;

import com.altech.employees.domain.dto.EmployeeDto;
import com.altech.employees.domain.entity.Employee;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EmployeeMapper {
    private final ModelMapper mapper;

    public EmployeeDto toDto(Employee employee) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return mapper.map(employee, EmployeeDto.class);
    }

    public Employee toDbo(EmployeeDto employeeDto) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return mapper.map(employeeDto, Employee.class);
    }

    public List<EmployeeDto> toDtoList(Iterable<Employee> employees) {
        return ((List<Employee>) employees).stream().map(this::toDto).collect(Collectors.toList());
    }
}

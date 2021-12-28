package com.altech.employees.service;

import com.altech.employees.domain.dto.EmployeeDto;
import com.altech.employees.domain.dto.OrganizationDto;
import com.altech.employees.domain.dto.StatusDto;
import com.altech.employees.domain.mapper.EmployeeMapper;
import com.altech.employees.domain.mapper.OrganizationMapper;
import com.altech.employees.domain.mapper.StatusMapper;
import com.altech.employees.repository.EmployeeRepository;
import com.altech.employees.repository.OrganizationRepository;
import com.altech.employees.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CRMService {
    private final EmployeeRepository employeeRepository;
    private final OrganizationRepository organizationRepository;
    private final StatusRepository statusRepository;

    private final EmployeeMapper employeeMapper;
    private final OrganizationMapper organizationMapper;
    private final StatusMapper statusMapper;

    public List<EmployeeDto> findEmployees(String findingQuery) {
        if (findingQuery.isEmpty()) {
            return employeeMapper.toDtoList(employeeRepository.findAll());
        } else {
            return employeeMapper.toDtoList(employeeRepository.search(findingQuery));
        }
    }

    public Long countEmployees() {
        return employeeRepository.count();
    }

    public void saveEmployee(EmployeeDto employee) {
        if (employee == null) {
            System.err.println("");
        } else if (employee.getId() != null) {
            employeeRepository.update(employeeMapper.toEntity(employee));
        } else {
            employeeRepository.save(employeeMapper.toEntity(employee));
        }
    }

    public void deleteEmployee(EmployeeDto employeeDto) {
        employeeRepository.delete(employeeMapper.toEntity(employeeDto));
    }

    public List<OrganizationDto> findAllOrganizations() {
        return organizationMapper.toDtoList(organizationRepository.findAll());
    }

    public List<StatusDto> findAllStatuses() {
        return statusMapper.toDtoList(statusRepository.findAll());
    }

    public Integer getEmployeeCount(Integer id) {
        return organizationRepository.getEmployeeCount(id);
    }
}

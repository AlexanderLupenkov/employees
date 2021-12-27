package com.altech.employees.repository;

import com.altech.employees.domain.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeRepository {
    List<Employee> findAll();

    Employee getById(Integer id);

    void save(Employee employee);

    void saveAll(List<Employee> employees);

    int update(Employee employee);

    void delete(Employee employee);

    Long count();

//  @Select("select c from Contact c " +
//            "where lower(c.firstName) like lower(concat('%',:string,'%'))" +
//            "or lower(c.lastName) like lower(concat('%',:string,'%'))")
//  List<Employee> search(String string);
}

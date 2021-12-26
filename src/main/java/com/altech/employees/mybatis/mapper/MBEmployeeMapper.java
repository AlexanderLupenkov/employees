package com.altech.employees.mybatis.mapper;

import com.altech.employees.domain.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MBEmployeeMapper {
    @Select("select * from employees")
    List<Employee> findAll();
}

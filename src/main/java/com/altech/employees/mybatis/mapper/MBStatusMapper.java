package com.altech.employees.mybatis.mapper;

import com.altech.employees.domain.entity.Employee;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MBStatusMapper {
    @Select("select * from status")
    List<Employee> findAll();
}

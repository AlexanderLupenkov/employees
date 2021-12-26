package com.altech.employees;

import com.altech.employees.domain.entity.Employee;
import com.altech.employees.domain.entity.Organization;
import com.altech.employees.domain.entity.Status;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.altech.employees.config.db.mapper")
@MappedTypes({Employee.class, Organization.class, Status.class})
public class EmployeesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
    }

}

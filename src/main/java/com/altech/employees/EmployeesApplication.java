package com.altech.employees;

import com.altech.employees.domain.entity.Employee;
import com.altech.employees.domain.entity.Organization;
import com.altech.employees.domain.entity.Status;
import com.vaadin.flow.component.dependency.NpmPackage;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.vaadin.artur.helpers.LaunchUtil;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@MappedTypes({Employee.class, Organization.class, Status.class})
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class EmployeesApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(EmployeesApplication.class, args));
    }
}

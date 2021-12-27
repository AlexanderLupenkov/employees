package com.altech.employees.utils.generator;

import com.altech.employees.domain.entity.Employee;
import com.altech.employees.domain.entity.Organization;
import com.altech.employees.domain.entity.Status;
import com.altech.employees.repository.MBEmployeeMapper;
import com.altech.employees.repository.MBOrganizationMapper;
import com.altech.employees.repository.MBStatusMapper;
import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(MBEmployeeMapper employeeMapper, MBOrganizationMapper organizationMapper,
                                      MBStatusMapper statusMapper) {

        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (employeeMapper.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");
            ExampleDataGenerator<Organization> orgGenerator = new ExampleDataGenerator<>(Organization.class,
                    LocalDateTime.now());
            orgGenerator.setData(Organization::setName, DataType.COMPANY_NAME);
            organizationMapper.saveAll(orgGenerator.create(5, seed));
            List<Organization> organizations = organizationMapper.findAll();

            statusMapper
                    .saveAll(Stream.of("Imported lead", "Not contacted", "Contacted", "Customer", "Closed (lost)")
                            .map(Status::new).collect(Collectors.toList()));

            List<Status> statuses = statusMapper.findAll();

            logger.info("... generating 50 Contact entities...");
            ExampleDataGenerator<Employee> employeeGenerator = new ExampleDataGenerator<>(Employee.class,
                    LocalDateTime.now());
            employeeGenerator.setData(Employee::setFirstName, DataType.FIRST_NAME);
            employeeGenerator.setData(Employee::setLastName, DataType.LAST_NAME);
            employeeGenerator.setData(Employee::setEmail, DataType.EMAIL);

            Random r = new Random(seed);
            List<Employee> contacts = employeeGenerator.create(50, seed).stream().map(contact -> {
                contact.setOrganization(organizations.get(r.nextInt(organizations.size())));
                contact.setStatus(statuses.get(r.nextInt(statuses.size())));
                return contact;
            }).collect(Collectors.toList());

            employeeMapper.saveAll(contacts);

            logger.info("Generated demo data");
        };
    }
}

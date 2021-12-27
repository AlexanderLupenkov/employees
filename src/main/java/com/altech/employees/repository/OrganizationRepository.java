package com.altech.employees.repository;

import com.altech.employees.domain.entity.Organization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizationRepository {
    List<Organization> findAll();

    Organization findById(Integer id);

    void saveAll(List<Organization> organizations);
}

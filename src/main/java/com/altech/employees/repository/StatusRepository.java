package com.altech.employees.repository;

import com.altech.employees.domain.entity.Status;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatusRepository {
    List<Status> findAll();

    Status findById(Integer id);

    void saveAll(List<Status> statuses);
}

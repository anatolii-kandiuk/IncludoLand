package com.includoland.includoland.repository;

import com.includoland.includoland.model.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByUserUuidOrderByIdDesc(String userUuid);
}

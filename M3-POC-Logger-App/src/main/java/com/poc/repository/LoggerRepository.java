package com.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.entity.LoggerEntity;

@Repository
public interface LoggerRepository extends JpaRepository<LoggerEntity, Long> {

}

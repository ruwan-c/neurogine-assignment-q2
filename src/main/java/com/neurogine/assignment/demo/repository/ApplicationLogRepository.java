package com.neurogine.assignment.demo.repository;

import com.neurogine.assignment.demo.entity.ApplicationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationLogRepository extends JpaRepository<ApplicationLog, Long> {
}
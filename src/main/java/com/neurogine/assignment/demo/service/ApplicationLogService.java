package com.neurogine.assignment.demo.service;

import com.neurogine.assignment.demo.entity.ApplicationLog;
import com.neurogine.assignment.demo.repository.ApplicationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationLogService {
    @Autowired
    private ApplicationLogRepository applicationLogRepository;

    public void saveApplicationLog(ApplicationLog applicationLog) {
        applicationLogRepository.save(applicationLog);
    }
}
package com.example.demo.Service;

import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface StudentDeleteService {
    @Transactional
    int deleteStudentByRollEntity(int roll);
}

package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.StudentData;

public interface StudentupdateRepository extends JpaRepository<StudentData, Integer>{
    @Override
    Optional<StudentData> findById(Integer studentId);
     
    @Override
    public <S extends StudentData> S save(S entity);
    
}

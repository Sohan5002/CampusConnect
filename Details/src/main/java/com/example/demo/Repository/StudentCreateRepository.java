package com.example.demo.Repository;

import com.example.demo.model.StudentData;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentCreateRepository  extends JpaRepository<StudentData, Integer>{
    @SuppressWarnings("null")
    @Override 
    public <S extends StudentData> S save(S entity);
}

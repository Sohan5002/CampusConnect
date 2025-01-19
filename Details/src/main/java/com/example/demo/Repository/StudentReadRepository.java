package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StudentData;
@Repository
public interface StudentReadRepository extends JpaRepository<StudentData, Integer> {
    
@SuppressWarnings("null")
@Override
List<StudentData> findAll();
List<StudentData> findByStudentName(String studentName);
List<StudentData> findByRoll(int roll);
List<StudentData> findByStudentNameAndRoll(String studentName, int roll);

}

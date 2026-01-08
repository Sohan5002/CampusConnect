package com.example.demo.Repository;

import com.example.demo.model.StudentData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentData, Long> {
    
    Optional<StudentData> findByRollNo(Integer rollNo);
    
    Optional<StudentData> findByEmail(String email);
    
    List<StudentData> findByNameContainingIgnoreCase(String name);
    
    List<StudentData> findByDepartment(String department);
    
    List<StudentData> findByDepartmentAndNameContainingIgnoreCase(String department, String name);
    
    Page<StudentData> findAll(Pageable pageable);
    
    Page<StudentData> findByDepartment(String department, Pageable pageable);
    
    Page<StudentData> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
    @Query("SELECT s FROM StudentData s WHERE s.rollNo = :rollNo AND s.name LIKE %:name%")
    List<StudentData> findByRollNoAndNameContaining(@Param("rollNo") Integer rollNo, @Param("name") String name);
}


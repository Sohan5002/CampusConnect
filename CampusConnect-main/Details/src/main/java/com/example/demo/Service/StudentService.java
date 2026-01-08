package com.example.demo.Service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.StudentData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    StudentData createStudent(StudentDTO studentDTO);
    StudentData updateStudent(Long id, StudentDTO studentDTO);
    void deleteStudent(Long id);
    StudentData getStudentById(Long id);
    StudentData getStudentByRollNo(Integer rollNo);
    List<StudentData> getAllStudents();
    Page<StudentData> getAllStudents(Pageable pageable);
    List<StudentData> getStudentsByName(String name);
    List<StudentData> getStudentsByDepartment(String department);
    Page<StudentData> getStudentsByDepartment(String department, Pageable pageable);
    List<StudentData> searchStudents(Integer rollNo, String name);
}


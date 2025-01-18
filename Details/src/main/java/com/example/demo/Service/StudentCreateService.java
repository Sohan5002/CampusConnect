package com.example.demo.Service;

import com.example.demo.model.StudentData;
//import com.example.demo.dto.StudentDTO;
import com.example.demo.dto.StudentDTO; // Add this import statement

public interface StudentCreateService {
    public StudentData addStudent(StudentDTO studentDTO); // Correct the parameter type and name


}

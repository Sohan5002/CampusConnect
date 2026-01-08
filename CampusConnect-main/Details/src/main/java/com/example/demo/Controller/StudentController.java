package com.example.demo.Controller;


import com.example.demo.dto.StudentDTO;
import com.example.demo.model.StudentData;
import com.example.demo.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
    public ResponseEntity<Map<String, Object>> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        StudentData student = studentService.createStudent(studentDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Student created successfully");
        response.put("data", student);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
    public ResponseEntity<Map<String, Object>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentDTO studentDTO) {
        StudentData student = studentService.updateStudent(id, studentDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Student updated successfully");
        response.put("data", student);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Student deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY', 'STUDENT')")
    public ResponseEntity<StudentData> getStudentById(@PathVariable Long id) {
        StudentData student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/roll/{rollNo}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY', 'STUDENT')")
    public ResponseEntity<StudentData> getStudentByRollNo(@PathVariable Integer rollNo) {
        StudentData student = studentService.getStudentByRollNo(rollNo);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    public ResponseEntity<Map<String, Object>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<StudentData> students = studentService.getAllStudents(pageable);
        
        Map<String, Object> response = new HashMap<>();
        response.put("data", students.getContent());
        response.put("currentPage", students.getNumber());
        response.put("totalItems", students.getTotalElements());
        response.put("totalPages", students.getTotalPages());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    public ResponseEntity<List<StudentData>> getStudentsByName(@PathVariable String name) {
        List<StudentData> students = studentService.getStudentsByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/department/{department}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    public ResponseEntity<Map<String, Object>> getStudentsByDepartment(
            @PathVariable String department,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentData> students = studentService.getStudentsByDepartment(department, pageable);
        
        Map<String, Object> response = new HashMap<>();
        response.put("data", students.getContent());
        response.put("currentPage", students.getNumber());
        response.put("totalItems", students.getTotalElements());
        response.put("totalPages", students.getTotalPages());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    public ResponseEntity<List<StudentData>> searchStudents(
            @RequestParam(required = false) Integer rollNo,
            @RequestParam(required = false) String name) {
        List<StudentData> students = studentService.searchStudents(rollNo, name);
        return ResponseEntity.ok(students);
    }
}


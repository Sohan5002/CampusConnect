package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_data", indexes = {
    @Index(name = "idx_roll_no", columnList = "rollNo"),
    @Index(name = "idx_email", columnList = "email"),
    @Index(name = "idx_department", columnList = "department")
})
public class StudentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roll_no", unique = true, nullable = false)
    private Integer rollNo;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "department", nullable = false, length = 100)
    private String department;

    @Column(name = "marks")
    private Double marks;

    @Column(name = "grade", length = 2)
    private String grade;

    // Additional fields for comprehensive student management
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "contact_number", unique = true, length = 15)
    private String contactNumber;

    @Column(name = "home_address", length = 255)
    private String homeAddress;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    @Column(name = "program", length = 100)
    private String program;

    @Column(name = "father_name", length = 100)
    private String fatherName;

    @Column(name = "mother_name", length = 100)
    private String motherName;
}

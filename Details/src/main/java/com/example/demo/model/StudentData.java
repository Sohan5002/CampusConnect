package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student_Data")
public class StudentData {

    
  @Column(unique = true)  
    private int roll;

   @Column(unique = true)
    private String studentId;

    private String firstName;

   
    private LocalDate dateOfBirth;

    private String gender;
   @Column(unique = true)
    private String contactNumber;

    @Column(unique = true)
    private String emailAddress;

    private String homeAddress;

   
    private LocalDate enrollmentDate;

   
    private String program;

   
    private String Fathername;
    private String Mothername;
}

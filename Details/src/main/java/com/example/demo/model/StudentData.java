package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student_Data")
public class StudentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
  @Column(unique = true)  
    private int roll;

 
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

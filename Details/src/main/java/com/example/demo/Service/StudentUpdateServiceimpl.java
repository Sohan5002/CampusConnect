package com.example.demo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Service.StudentUpdateService;
import com.example.demo.model.StudentData;
import com.example.demo.model.StudentDTO; // Import StudentDTO

import com.example.demo.Repository.StudentupdateRepository;

@Service
public class StudentUpdateServiceimpl implements StudentUpdateService {
   @Autowired
    private StudentupdateRepository studentUpdateRepository;
    @Override
    public String updateStudent(int studentId, StudentDTO studentDTO) {
    
        Optional<StudentData>std=studentUpdateRepository.findById(studentId);
    if(!std.isPresent())
{
    return "Student not found";
}
    else{
        StudentData studentData = std.get();
        if (studentDTO.getRoll() !=0) {
            studentData.setRoll(studentDTO.getRoll());
            
        }

     if (studentDTO.getFirstName() != null && !studentDTO.getFirstName().isBlank()) {
            studentData.setFirstName(studentDTO.getFirstName());
        }
        if (studentDTO.getDateOfBirth() != null) {
            studentData.setDateOfBirth(studentDTO.getDateOfBirth());
        }
        if (studentDTO.getGender() != null && !studentDTO.getGender().isBlank()) {
            studentData.setGender(studentDTO.getGender());
        }
        if (studentDTO.getContactNumber() != null && !studentDTO.getContactNumber().isBlank()) {
            studentData.setContactNumber(studentDTO.getContactNumber());
        }
        if (studentDTO.getEmailAddress() != null && !studentDTO.getEmailAddress().isBlank()) {
            studentData.setEmailAddress(studentDTO.getEmailAddress());
        }
        if (studentDTO.getHomeAddress() != null && !studentDTO.getHomeAddress().isBlank()) {
            studentData.setHomeAddress(studentDTO.getHomeAddress());
        }
        if (studentDTO.getEnrollmentDate() != null) {
            studentData.setEnrollmentDate(studentDTO.getEnrollmentDate());
        }
        if (studentDTO.getProgram() != null && !studentDTO.getProgram().isBlank()) {
            studentData.setProgram(studentDTO.getProgram());
        }
        if (studentDTO.getFathername() != null && !studentDTO.getFathername().isBlank()) {
            studentData.setFathername(studentDTO.getFathername());
        }
        if (studentDTO.getMothername() != null && !studentDTO.getMothername().isBlank()) {
            studentData.setMothername(studentDTO.getMothername());
        }
    
        // Save the updated student data
        StudentData savedStudentData = studentUpdateRepository.save(studentData);
    if(savedStudentData != null)
    {
        return "Student updated successfully";

    
    }
    else
    {
        return "Student not updated";
    }
}
     return null;
    
    }
    
}

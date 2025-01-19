package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.StudentReadRepository;
import com.example.demo.model.StudentData;
 @Service
public class StudentReadServiceimpl implements StudentReadService {
    @Autowired
    private  StudentReadRepository studentReadRepository;
 
 @Override
    public List<StudentData> findAll() {
	 // Implement the method body
	 return studentReadRepository.findAll();
 }
@Override
 public List<StudentData> findByStudentName(String studentName) 
{
	// Implement the method body
	return studentReadRepository.findByStudentName(studentName);
}
@Override
 public List<StudentData> findByRoll(int roll) {
	// Implement the method body
	return studentReadRepository.findByRoll(roll);
}
  @Override
 public List<StudentData> findByStudentNameAndRoll(String studentName, int roll) {
	// Implement the method body
	return studentReadRepository.findByStudentNameAndRoll(studentName, roll);
}
   
}

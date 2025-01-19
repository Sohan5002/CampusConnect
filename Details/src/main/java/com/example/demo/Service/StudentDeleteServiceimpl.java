package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.StudentDeleteRepository;

@Service
public class StudentDeleteServiceimpl implements StudentDeleteService {
    @Autowired
    private StudentDeleteRepository studentDeleteRepository;    
    @Override
    public int deleteStudentByRollEntity(int roll) {
        // Implement the method body
        //return 0;
     return studentDeleteRepository.deleteStudentByRollEntity(roll);
    }
    
}

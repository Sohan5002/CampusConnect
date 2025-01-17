package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.StudentData;
import com.example.demo.Repository.StudentCreateRepository;

@Service
public class StudentCreateServiceImpl implements StudentCreateService {

    @Autowired
    private StudentCreateRepository studentCreateRepository;

    @Override
    public StudentData addStudent(StudentData studentData) {
        return studentCreateRepository.save(studentData);
    }
}

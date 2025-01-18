package com.example.demo.Service;

import java.util.List;

import com.example.demo.model.StudentData;

public interface StudentReadService {List<StudentData> findAll();
List<StudentData> findByStudentName(String studentName);
List<StudentData> findByRoll(int roll);
List<StudentData> findByStudentNameAndRoll(String studentName, int roll);

}

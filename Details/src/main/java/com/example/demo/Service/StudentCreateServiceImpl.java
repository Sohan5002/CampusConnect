package com.example.demo.Service;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.StudentData;
import com.example.demo.Repository.StudentCreateRepository;
import com.example.demo.dto.*;

@Service
public class StudentCreateServiceImpl implements StudentCreateService {

    @Autowired
    private StudentCreateRepository studentCreateRepository;

    @Override
    public StudentData addStudent(StudentDTO studentDTO) {
      
       ModelMapper modelMapper = new ModelMapper();
        PropertyMap<StudentDTO,StudentData>propertyMap=new PropertyMap<StudentDTO, StudentData>() {
        @Override
           protected void configure() {
              
            skip(destination.getStudentId());
            //   map().setStudentAddress(source.getStudentAddress());
           }
       };
         modelMapper.addMappings(propertyMap);   
      
       StudentData studentData = modelMapper.map(studentDTO, StudentData.class);
        return studentCreateRepository.save(studentData);
    }
}

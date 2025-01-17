package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.StudentCreateService;
import com.example.demo.model.StudentData;

import jakarta.validation.Valid;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/student")
public class StudentBasic {

    @Autowired
    private StudentCreateService studentCreateService;

    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@Valid  @RequestBody StudentData studentData, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
          for(FieldError error : bindingResult.getFieldErrors()) {
            hashMap.put(error.getField(), error.getDefaultMessage());
          }
        
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        else {
            studentData = studentCreateService.addStudent(studentData);
            return new ResponseEntity<>(studentData, HttpStatus.CREATED);
            
            
        }
    }
}

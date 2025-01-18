package com.example.demo.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Service.StudentCreateService;
import com.example.demo.Service.StudentReadService;
import com.example.demo.model.StudentData;
import com.example.demo.dto.StudentDTO;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/student")
public class StudentBasic {

    @Autowired
    private StudentCreateService studentCreateService;
   private StudentReadService studentReadService;
    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@Valid  @RequestBody StudentDTO  studentDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
          for(FieldError error : bindingResult.getFieldErrors()) {
            hashMap.put(error.getField(), error.getDefaultMessage());
          }
        
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        else {
            StudentData studentData = studentCreateService.addStudent(studentDTO);
            return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
            
            
        }
    }


     @GetMapping("/all")
    public ResponseEntity<List<StudentData>> getAllStudents() {
        List<StudentData> students = studentReadService.findAll();
        return ResponseEntity.ok(students);
    }

    // Endpoint to fetch students by name
    @GetMapping("/name/{studentName}")
    public ResponseEntity<List<StudentData>> getStudentsByName(@PathVariable String studentName) {
        List<StudentData> students = studentReadService.findByStudentName(studentName);
        return ResponseEntity.ok(students);
    }

    // Endpoint to fetch students by roll
    @GetMapping("/roll/{roll}")
    public ResponseEntity<List<StudentData>> getStudentsByRoll(@PathVariable int roll) {
        List<StudentData> students = studentReadService.findByRoll(roll);
        return ResponseEntity.ok(students);
    }

    // Endpoint to fetch students by name and roll
    @GetMapping("/search")
    public ResponseEntity<List<StudentData>> getStudentsByNameAndRoll(
            @RequestParam String studentName, 
            @RequestParam int roll) {
        List<StudentData> students = studentReadService.findByStudentNameAndRoll(studentName, roll);
        return ResponseEntity.ok(students);
    }
}


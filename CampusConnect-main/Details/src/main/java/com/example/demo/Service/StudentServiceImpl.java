package com.example.demo.Service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.StudentData;
import com.example.demo.Repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentData createStudent(StudentDTO studentDTO) {
        // Check if rollNo already exists
        if (studentDTO.getRollNo() != null && studentRepository.findByRollNo(studentDTO.getRollNo()).isPresent()) {
            throw new IllegalArgumentException("Student with roll number " + studentDTO.getRollNo() + " already exists");
        }
        
        // Check if email already exists
        if (studentDTO.getEmail() != null && studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Student with email " + studentDTO.getEmail() + " already exists");
        }

        StudentData studentData = modelMapper.map(studentDTO, StudentData.class);
        return studentRepository.save(studentData);
    }

    @Override
    public StudentData updateStudent(Long id, StudentDTO studentDTO) {
        StudentData existingStudent = studentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Student with id " + id + " not found"));

        // Check if rollNo is being changed and if new rollNo already exists
        if (studentDTO.getRollNo() != null && !studentDTO.getRollNo().equals(existingStudent.getRollNo())) {
            if (studentRepository.findByRollNo(studentDTO.getRollNo()).isPresent()) {
                throw new IllegalArgumentException("Student with roll number " + studentDTO.getRollNo() + " already exists");
            }
            existingStudent.setRollNo(studentDTO.getRollNo());
        }

        // Check if email is being changed and if new email already exists
        if (studentDTO.getEmail() != null && !studentDTO.getEmail().equals(existingStudent.getEmail())) {
            if (studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Student with email " + studentDTO.getEmail() + " already exists");
            }
            existingStudent.setEmail(studentDTO.getEmail());
        }

        // Update other fields
        if (studentDTO.getName() != null && !studentDTO.getName().isBlank()) {
            existingStudent.setName(studentDTO.getName());
        }
        if (studentDTO.getDepartment() != null && !studentDTO.getDepartment().isBlank()) {
            existingStudent.setDepartment(studentDTO.getDepartment());
        }
        if (studentDTO.getMarks() != null) {
            existingStudent.setMarks(studentDTO.getMarks());
        }
        if (studentDTO.getGrade() != null) {
            existingStudent.setGrade(studentDTO.getGrade());
        }
        if (studentDTO.getDateOfBirth() != null) {
            existingStudent.setDateOfBirth(studentDTO.getDateOfBirth());
        }
        if (studentDTO.getGender() != null) {
            existingStudent.setGender(studentDTO.getGender());
        }
        if (studentDTO.getContactNumber() != null) {
            existingStudent.setContactNumber(studentDTO.getContactNumber());
        }
        if (studentDTO.getHomeAddress() != null) {
            existingStudent.setHomeAddress(studentDTO.getHomeAddress());
        }
        if (studentDTO.getEnrollmentDate() != null) {
            existingStudent.setEnrollmentDate(studentDTO.getEnrollmentDate());
        }
        if (studentDTO.getProgram() != null) {
            existingStudent.setProgram(studentDTO.getProgram());
        }
        if (studentDTO.getFatherName() != null) {
            existingStudent.setFatherName(studentDTO.getFatherName());
        }
        if (studentDTO.getMotherName() != null) {
            existingStudent.setMotherName(studentDTO.getMotherName());
        }

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student with id " + id + " not found");
        }
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentData getStudentById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Student with id " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public StudentData getStudentByRollNo(Integer rollNo) {
        return studentRepository.findByRollNo(rollNo)
            .orElseThrow(() -> new IllegalArgumentException("Student with roll number " + rollNo + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentData> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StudentData> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentData> getStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentData> getStudentsByDepartment(String department) {
        return studentRepository.findByDepartment(department);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StudentData> getStudentsByDepartment(String department, Pageable pageable) {
        return studentRepository.findByDepartment(department, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentData> searchStudents(Integer rollNo, String name) {
        return studentRepository.findByRollNoAndNameContaining(rollNo, name);
    }
}


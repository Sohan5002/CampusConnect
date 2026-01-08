package com.example.demo.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;

    @Min(value = 1, message = "Roll number must be greater than 0")
    @Max(value = 10000, message = "Roll number must be less than or equal to 10000")
    @NotNull(message = "Roll number cannot be null")
    private Integer rollNo;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Email address cannot be blank")
    @Email(message = "Email address must be valid")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @NotBlank(message = "Department cannot be blank")
    @Size(max = 100, message = "Department cannot exceed 100 characters")
    private String department;

    @Min(value = 0, message = "Marks must be greater than or equal to 0")
    @Max(value = 100, message = "Marks must be less than or equal to 100")
    private Double marks;

    private String grade;

    @Past(message = "Date of Birth must be in the past")
    private LocalDate dateOfBirth;

    @Pattern(regexp = "Male|Female|Other", message = "Gender must be 'Male', 'Female', or 'Other'")
    private String gender;

    @Pattern(regexp = "^\\d{10}$", message = "Contact number must be a 10-digit number")
    private String contactNumber;

    @Size(max = 255, message = "Home address cannot exceed 255 characters")
    private String homeAddress;

    @PastOrPresent(message = "Enrollment date must be in the past or today")
    private LocalDate enrollmentDate;

    @Size(max = 100, message = "Program cannot exceed 100 characters")
    private String program;

    @Size(max = 100, message = "Father's name cannot exceed 100 characters")
    private String fatherName;

    @Size(max = 100, message = "Mother's name cannot exceed 100 characters")
    private String motherName;
}

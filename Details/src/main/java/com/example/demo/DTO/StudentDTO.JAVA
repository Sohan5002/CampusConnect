package com.example.demo.dto;
import java.time.LocalDate;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;





@Data
public class StudentDTO {

    @Min(value = 1, message = "Roll number must be greater than 0")
    @Max(value = 1000, message = "Roll number must be less than or equal to 1000")
    private int roll;

    

    @NotBlank(message = "First name cannot be blank")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;

    @NotNull(message = "Date of Birth cannot be null")
    @Past(message = "Date of Birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "Male|Female|Other", message = "Gender must be 'Male', 'Female', or 'Other'")
    private String gender;

    @NotBlank(message = "Contact number cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "Contact number must be a 10-digit number")
    private String contactNumber;

    @NotBlank(message = "Email address cannot be blank")
    @Email(message = "Email address must be valid")
    private String emailAddress;

    @NotBlank(message = "Home address cannot be blank")
    @Size(max = 255, message = "Home address cannot exceed 255 characters")
    private String homeAddress;

    @NotNull(message = "Enrollment date cannot be null")
    @PastOrPresent(message = "Enrollment date must be in the past or today")
    private LocalDate enrollmentDate;

    @NotBlank(message = "Program cannot be blank")
    @Size(max = 100, message = "Program cannot exceed 100 characters")
    private String program;

    @NotBlank(message = "Father's name cannot be blank")
    @Size(max = 50, message = "Father's name cannot exceed 50 characters")
    private String Fathername;

    @NotBlank(message = "Mother's name cannot be blank")
    @Size(max = 50, message = "Mother's name cannot exceed 50 characters")
    private String Mothername;

}
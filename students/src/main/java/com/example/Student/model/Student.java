// src/main/java/com/example/student/model/Student.java
package com.example.Student.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 5, message = "Age must be at least 5")
    @Max(value = 100, message = "Age must be less than 100")
    private int age;

    @NotBlank(message = "Class is required")
    private String studentClass;

    @Email(message = "Invalid email format")
    private String email;

    private String address;

    public void setStudentId(long id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setStudentId'");
    }

    // Getters and Setters
}

// src/main/java/com/example/student/service/StudentService.java
package com.example.Student.service;

import com.example.Student.model.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    List<Student> searchByName(String name);
    List<Student> searchByClass(String studentClass);
}

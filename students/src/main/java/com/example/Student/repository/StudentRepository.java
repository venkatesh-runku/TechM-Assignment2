package com.example.Student.repository;

import com.example.Student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCase(String name);
    List<Student> findByStudentClassContainingIgnoreCase(String studentClass);
}
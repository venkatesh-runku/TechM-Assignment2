// src/main/java/com/example/student/controller/StudentController.java
package com.example.Student.controller;

import com.example.Student.model.Student;
import com.example.Student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "students";
    }

    @GetMapping("/add")
    public String showAddForm(Student student) {
        return "add-student";
    }

    @PostMapping("/add")
    public String addStudent(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }
        service.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Student student = service.getStudentById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return "update-student";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            student.setStudentId(id);
            return "update-student";
        }
        service.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id) {
        service.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        List<Student> result = service.searchByName(keyword);
        model.addAttribute("students", result);
        return "students";
    }
}

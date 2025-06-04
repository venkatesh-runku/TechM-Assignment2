package com.example.attendance.controller;

import com.example.attendance.entity.Attendance;
import com.example.attendance.entity.Employee;
import com.example.attendance.repository.EmployeeRepository;
import com.example.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/mark")
    public String markAttendance(@AuthenticationPrincipal User user, @RequestParam("status") String status) {
        Employee employee = employeeRepository.findByName(user.getUsername()).orElseThrow();
        Attendance.Status attendanceStatus = Attendance.Status.valueOf(status.toUpperCase());
        attendanceService.markAttendance(employee, attendanceStatus);
        return "Attendance marked as " + attendanceStatus;
    }

    @GetMapping("/report")
    public List<Attendance> getMyAttendance(@AuthenticationPrincipal User user) {
        Employee employee = employeeRepository.findByName(user.getUsername()).orElseThrow();
        return attendanceService.getEmployeeAttendance(employee);
    }
}

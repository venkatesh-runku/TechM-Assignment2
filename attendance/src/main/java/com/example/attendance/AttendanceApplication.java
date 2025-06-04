package com.example.attendance;

import com.example.attendance.entity.Employee;
import com.example.attendance.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AttendanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceApplication.class, args);
    }

    // Create some employees for testing
    @Bean
    CommandLineRunner init(EmployeeRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                Employee e1 = new Employee();
                e1.setName("john");
                e1.setDepartment("IT");
                e1.setDesignation("Developer");
                e1.setRole("EMPLOYEE");
                repo.save(e1);

                Employee e2 = new Employee();
                e2.setName("manager");
                e2.setDepartment("IT");
                e2.setDesignation("Manager");
                e2.setRole("MANAGER");
                repo.save(e2);
            }
        };
    }
}

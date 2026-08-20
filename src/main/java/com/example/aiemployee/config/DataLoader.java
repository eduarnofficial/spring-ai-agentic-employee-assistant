package com.example.aiemployee.config;

import com.example.aiemployee.employee.Employee;
import com.example.aiemployee.employee.EmployeeRepository;
import com.example.aiemployee.leave.LeaveBalance;
import com.example.aiemployee.leave.LeaveBalanceRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(
            EmployeeRepository employeeRepository,
            LeaveBalanceRepository leaveRepository) {

        return args -> {

            employeeRepository.save(
                    new Employee(
                            101L,
                            "Rahul Sharma",
                            "IT",
                            "rahul@company.com",
                            "Senior Developer"
                    )
            );

            employeeRepository.save(
                    new Employee(
                            102L,
                            "Priya Kumar",
                            "HR",
                            "priya@company.com",
                            "HR Manager"
                    )
            );

            leaveRepository.save(
                    new LeaveBalance(
                            101L,
                            18,
                            8,
                            10
                    )
            );

            leaveRepository.save(
                    new LeaveBalance(
                            102L,
                            12,
                            6,
                            6
                    )
            );
        };
    }
}

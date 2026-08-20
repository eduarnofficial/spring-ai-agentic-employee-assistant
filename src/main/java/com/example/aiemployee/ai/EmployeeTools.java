package com.example.aiemployee.ai;

import com.example.aiemployee.employee.Employee;
import com.example.aiemployee.employee.EmployeeRepository;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class EmployeeTools {

    private final EmployeeRepository repository;

    public EmployeeTools(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Tool(
        name = "getEmployee",
        description = """
            Retrieve employee information from the employee database.
            Use this tool when the user asks about an employee.
            """
    )
    public Employee getEmployee(
            @ToolParam(description = "Employee ID") Long employeeId) {

        return repository.findById(employeeId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Employee not found: " + employeeId
                        ));
    }
}

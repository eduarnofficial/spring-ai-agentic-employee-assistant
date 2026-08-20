package com.example.aiemployee.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    private Long id;

    private String name;

    private String department;

    private String email;

    private String designation;

    public Employee() {
    }

    public Employee(Long id,
                    String name,
                    String department,
                    String email,
                    String designation) {

        this.id = id;
        this.name = name;
        this.department = department;
        this.email = email;
        this.designation = designation;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public String getDesignation() {
        return designation;
    }
}

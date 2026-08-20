package com.example.aiemployee.leave;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LeaveBalance {

    @Id
    private Long employeeId;

    private int availableLeaves;

    private int sickLeaves;

    private int casualLeaves;

    public LeaveBalance() {
    }

    public LeaveBalance(Long employeeId,
                         int availableLeaves,
                         int sickLeaves,
                         int casualLeaves) {

        this.employeeId = employeeId;
        this.availableLeaves = availableLeaves;
        this.sickLeaves = sickLeaves;
        this.casualLeaves = casualLeaves;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public int getAvailableLeaves() {
        return availableLeaves;
    }

    public int getSickLeaves() {
        return sickLeaves;
    }

    public int getCasualLeaves() {
        return casualLeaves;
    }
}

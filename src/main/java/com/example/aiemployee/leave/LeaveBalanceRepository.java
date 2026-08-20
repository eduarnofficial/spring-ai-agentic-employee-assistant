package com.example.aiemployee.leave;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveBalanceRepository
        extends JpaRepository<LeaveBalance, Long> {
}

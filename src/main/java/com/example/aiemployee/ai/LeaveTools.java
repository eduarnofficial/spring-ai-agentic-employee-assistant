package com.example.aiemployee.ai;

import com.example.aiemployee.leave.LeaveBalance;
import com.example.aiemployee.leave.LeaveBalanceRepository;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class LeaveTools {

    private final LeaveBalanceRepository repository;

    public LeaveTools(LeaveBalanceRepository repository) {
        this.repository = repository;
    }

    @Tool(
        name = "getLeaveBalance",
        description = """
            Retrieve an employee's leave balance.
            Use this tool when the user asks about available,
            sick, casual, or remaining leaves.
            """
    )
    public LeaveBalance getLeaveBalance(
            @ToolParam(description = "Employee ID") Long employeeId) {

        return repository.findById(employeeId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave information not found for employee: "
                                + employeeId
                        ));
    }
}

package com.example.aiemployee.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAIService {

    private final ChatClient chatClient;

    private final EmployeeTools employeeTools;

    private final LeaveTools leaveTools;

    public EmployeeAIService(
            ChatClient.Builder builder,
            EmployeeTools employeeTools,
            LeaveTools leaveTools) {

        this.chatClient = builder.build();

        this.employeeTools = employeeTools;
        this.leaveTools = leaveTools;
    }

    public String ask(String question) {

        return chatClient
                .prompt()
                .system("""
                    You are an AI Employee Assistant.

                    You help employees with:
                    - Employee information
                    - Leave information
                    - HR policies

                    Rules:
                    1. Use tools when database information is required.
                    2. Never invent employee information.
                    3. Never invent leave balances.
                    4. Give concise and professional answers.
                    5. If required information is unavailable, say so.
                    """)
                .user(question)
                .tools(employeeTools, leaveTools)
                .call()
                .content();
    }
}

package com.example.aiemployee.ai;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class EmployeeAIController {

    private final EmployeeAIService aiService;

    public EmployeeAIController(EmployeeAIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/ask")
    public String ask(
            @RequestParam String question) {

        return aiService.ask(question);
    }
}

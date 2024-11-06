package com.spring.bioMedical.Controller;

import com.spring.bioMedical.rmi.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatbotService chatbotService;
    @PostMapping
    public String getChatbotResponse(@RequestBody String question) {
        try {
            return chatbotService.getResponse(question);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

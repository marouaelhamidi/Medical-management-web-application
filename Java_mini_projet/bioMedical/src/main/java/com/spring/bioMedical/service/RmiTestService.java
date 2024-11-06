package com.spring.bioMedical.service;

import com.spring.bioMedical.rmi.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RmiTestService {

    @Autowired
    private ChatbotService chatbotService;
    @PostConstruct
    public void testRmiService() {
        try {
            // Appelez une méthode du service RMI
            String response = chatbotService.getResponse("Hello");
            // Affichez la réponse
            System.out.println("Response from RMI service: " + response);
        } catch (Exception e) {
            // Gérez les exceptions
            e.printStackTrace();
        }
    }

}

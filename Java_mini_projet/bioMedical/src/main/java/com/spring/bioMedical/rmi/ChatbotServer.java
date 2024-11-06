package com.spring.bioMedical.rmi;

import  java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatbotServer {
    public static void main(String[] args) {
        try {
            ChatbotService chatbotService = new ChatbotServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("ChatbotService", chatbotService);
            System.out.println("Chatbot RMI Server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
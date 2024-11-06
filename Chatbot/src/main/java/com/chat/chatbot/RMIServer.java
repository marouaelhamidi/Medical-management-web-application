package com.chat.chatbot;

import com.chat.chatbot.ChatService;
import com.chat.chatbot.ChatServiceImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            ChatService chatService = new ChatServiceImpl();
            Naming.rebind("rmi://localhost:1099/ChatService", chatService);
            System.out.println("ChatService is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

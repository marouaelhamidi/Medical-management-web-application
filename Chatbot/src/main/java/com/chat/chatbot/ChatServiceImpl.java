package com.chat.chatbot;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {

    public ChatServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String getResponse(String question) throws RemoteException {
        // Ajoutez votre logique de traitement ici
        return "Réponse à la question : " + question;
    }
}

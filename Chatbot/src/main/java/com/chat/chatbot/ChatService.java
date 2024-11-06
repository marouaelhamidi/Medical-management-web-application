package com.chat.chatbot;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatService extends Remote {
    String getResponse(String question) throws RemoteException;
}

package com.chat.chatbot;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.rmi.Naming;

public class ChatbotApp extends Application {

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField inputField;

    @FXML
    private Button sendButton;

    private ChatService chatService;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            chatService = (ChatService) Naming.lookup("rmi://localhost:1099/ChatService");
        } catch (Exception e) {
            e.printStackTrace();
            // Vous pouvez ajouter un message d'erreur à l'utilisateur ici
        }

        // Charge le fichier FXML depuis le chemin spécifié
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/chatbot.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        primaryStage.setTitle("Chatbot");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void initialize() {
        // Associe l'action de cliquer sur le bouton d'envoi à la méthode sendMessage()
        sendButton.setOnAction(event -> sendMessage());
    }

    private void sendMessage() {
        String message = inputField.getText();
        if (!message.isEmpty()) {
            chatArea.appendText("You: " + message + "\n");
            try {
                String response = chatService.getResponse(message);
                chatArea.appendText("Bot: " + response + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                // Gérez les exceptions ici, par exemple en affichant un message d'erreur à l'utilisateur
                chatArea.appendText("Error: Unable to get response from the bot\n");
            }
            inputField.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

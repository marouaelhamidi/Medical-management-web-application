package com.chat.chatbotapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import okhttp3.*;

import org.json.JSONObject;

import java.io.IOException;

public class HelloApplication extends Application {

    private static final String CHATBOT_API_URL = "http://localhost:5000/api/chat";
    private VBox messagesContainer;
    private ScrollPane scrollPane;
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.initStyle(StageStyle.UNDECORATED); // Remove default window decorations

        BorderPane root = new BorderPane();

        // Custom title bar
        HBox titleBar = createTitleBar();
        root.setTop(titleBar);

        // Main content
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(30));
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setStyle("-fx-background-color: #282c34;");

        // Message container
        messagesContainer = new VBox(10);
        messagesContainer.setPadding(new Insets(10));
        messagesContainer.setStyle("-fx-background-color: #20232a;");

        // ScrollPane for message area
        scrollPane = new ScrollPane();
        scrollPane.setContent(messagesContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: #20232a; -fx-border-color: transparent;");

        // Input field and button
        TextField questionField = new TextField();
        questionField.setPromptText("Enter your question here...");
        questionField.setStyle("-fx-border-color: #61dafb; -fx-background-color: #20232a; -fx-text-fill: #abb2bf;");
        questionField.setPadding(new Insets(10));
        questionField.setFont(Font.font("Arial", 14));

        Button askButton = new Button("Ask");
        askButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        askButton.setStyle("-fx-background-color: #61dafb; -fx-text-fill: #282c34;");
        askButton.setPadding(new Insets(10));
        askButton.setOnMouseEntered(e -> askButton.setStyle("-fx-background-color: #21a1f1; -fx-text-fill: #282c34;"));
        askButton.setOnMouseExited(e -> askButton.setStyle("-fx-background-color: #61dafb; -fx-text-fill: #282c34;"));

        // Action button
        askButton.setOnAction(e -> {
            String question = questionField.getText();
            if (!question.isEmpty()) {
                addMessage(question, "user");
                try {
                    String response = sendQuestionToChatBot(question);
                    addMessage(response, "bot");
                } catch (IOException ex) {
                    addMessage("Error communicating with chatbot server.", "bot");
                }
                questionField.clear();
            }
        });

        VBox inputBox = new VBox(10, questionField, askButton);
        inputBox.setPadding(new Insets(10));
        inputBox.setAlignment(Pos.BOTTOM_CENTER);

        mainContent.getChildren().addAll(scrollPane, inputBox);

        root.setCenter(mainContent);

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createTitleBar() {
        HBox titleBar = new HBox();
        titleBar.setPadding(new Insets(5));
        titleBar.setStyle("-fx-background-color: #61dafb; -fx-border-color: #282c34; -fx-border-width: 0 0 2px 0;");
        titleBar.setAlignment(Pos.CENTER_RIGHT);

        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-font-weight: bold;");
        closeButton.setOnAction(e -> primaryStage.close());

        Button minimizeButton = new Button("_");
        minimizeButton.setStyle("-fx-background-color: #ffeb3b; -fx-text-fill: black; -fx-font-weight: bold;");
        minimizeButton.setOnAction(e -> primaryStage.setIconified(true));

        Button maximizeButton = new Button("[ ]");
        maximizeButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-weight: bold;");
        maximizeButton.setOnAction(e -> {
            if (primaryStage.isMaximized()) {
                primaryStage.setMaximized(false);
                maximizeButton.setText("[ ]");
            } else {
                primaryStage.setMaximized(true);
                maximizeButton.setText("[]");
            }
        });

        titleBar.getChildren().addAll(closeButton, minimizeButton, maximizeButton);
        return titleBar;
    }

    private void addMessage(String message, String type) {
        Label msgLabel = new Label(message);
        msgLabel.setFont(Font.font("Arial", 14));
        msgLabel.setWrapText(true);
        msgLabel.setPadding(new Insets(10));
        msgLabel.setMaxWidth(400);

        if (type.equals("user")) {
            msgLabel.setStyle("-fx-background-color: #61dafb; -fx-text-fill: #282c34; -fx-background-radius: 15px; -fx-alignment: center-right;");
            msgLabel.setAlignment(Pos.CENTER_RIGHT);
        } else {
            msgLabel.setStyle("-fx-background-color: #333; -fx-text-fill: #abb2bf; -fx-background-radius: 15px; -fx-alignment: center-left;");
            msgLabel.setAlignment(Pos.CENTER_LEFT);
        }

        messagesContainer.getChildren().add(msgLabel);
        scrollPane.setVvalue(1.0); // Scroll to bottom
    }

    private String sendQuestionToChatBot(String question) throws IOException {
        OkHttpClient client = new OkHttpClient();

        JSONObject json = new JSONObject();
        json.put("question", question);

        RequestBody body = RequestBody.create(
                json.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(CHATBOT_API_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            JSONObject responseJson = new JSONObject(response.body().string());
            return responseJson.getString("response");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

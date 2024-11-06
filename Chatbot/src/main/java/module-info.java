module com.chat.chatbot {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;
    requires java.rmi;


    opens com.chat.chatbot to javafx.fxml;
    exports com.chat.chatbot;
}
module com.chat.chatbotapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;
    requires okhttp3;

    opens com.chat.chatbotapp to javafx.fxml;
    exports com.chat.chatbotapp;
}
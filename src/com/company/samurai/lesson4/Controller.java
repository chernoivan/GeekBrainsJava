package com.company.samurai.lesson4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;

public class Controller {
    @FXML
    Button sendButton;

    @FXML
    TextField messageToChat;

    @FXML
    TextArea chatArea;

    public void sendMessage(ActionEvent actionEvent) {
        String newMessage = messageToChat.getText().trim();
            if (!newMessage.isEmpty()) {
                chatArea.appendText(newMessage+System.lineSeparator());
                messageToChat.clear();
            }
    }
}

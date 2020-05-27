package com.company.samurai.lesson4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    public void btnOneClickAction(ActionEvent actionEvent) {
        sendMessage(actionEvent, sendButton);
    }

    public void txtfPressEnter(ActionEvent actionEvent) {
        sendMessage(actionEvent, messageToChat);
    }

    private void sendMessage(ActionEvent actionEvent, Object object) {
        if (actionEvent.getSource().equals(object))
            if (messageToChat.getText().length() > 0) {
                chatArea.setText(messageToChat.getText());
                messageToChat.clear();
            }
    }
}

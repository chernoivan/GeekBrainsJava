package com.company.samurai.lesson8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.Socket;

public class AuthenticationDialog extends Application {
    public Socket socket = null;
    FXMLLoader loader;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader(getClass().getResource("authDialog.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

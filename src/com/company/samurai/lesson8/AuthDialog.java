package com.company.samurai.lesson8;

import com.company.samurai.lesson8.multiscene.ChatSceneApp;
import com.company.samurai.lesson8.multiscene.SceneFlow;
import com.company.samurai.lesson8.multiscene.Stageable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthDialog implements Stageable, Initializable {
    private Stage stage;
    public Socket socket;
    private boolean authOk = false;
    private static final int CLOSETIME = 120000;

    @FXML
    AnchorPane rootPane;

    @FXML
    TextField userName;

    @FXML
    PasswordField userPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Long startTime = System.currentTimeMillis();
        new Thread(()->{
            while (!authOk) {
                if (System.currentTimeMillis() - startTime > CLOSETIME && !authOk) {
                    Platform.runLater(() -> {
                        try {
                            ((Stage)rootPane.getScene().getWindow()).close();
                            System.out.println("Бездействие более 120 сек. Сервер закрыл соединение");
                        } catch (NullPointerException  e) {
                            e.printStackTrace();
                        }
                    });
                    break;
                }
            }
        }).start();

    }

    public void submitUserPassword(ActionEvent actionEvent) {
        TimeOutExit timeOutExit = new TimeOutExit();
        timeOutExit.stop();
        try {
            socket = ChatSceneApp.getScenes().get(SceneFlow.CHAT).getSocket();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String authMessage = "/auth " + userName.getText() + " " + userPassword.getText();
            out.writeUTF(authMessage);
            while (true) {
                if(in.available()>0) {
                    String strFromServer = in.readUTF();
                    if (strFromServer.startsWith("/authOk")) {
                        System.out.println("Authorized on server");
                        authOk = true;
                        ChatSceneApp.getScenes().get(SceneFlow.CHAT).setNick(strFromServer.split("\\s")[1]);
                        break;
                    }
                }
            }
            stage.setScene(ChatSceneApp.getScenes().get(SceneFlow.CHAT).getScene());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void exit(ActionEvent actionEvent) {
        ((Stage)rootPane.getScene().getWindow()).close();
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

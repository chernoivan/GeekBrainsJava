package com.company.samurai.lesson6;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainServer {
    private Socket client;
    private DataInputStream in;
    private static DataOutputStream out;

    public MainServer() {
        try {
            openServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void openServer() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");

            client = serverSocket.accept();
            System.out.println("Клиент подключился");

            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            while (!client.isClosed()) {
                String strFromClient = in.readUTF();
                if (strFromClient.equals("/end")) {
                    out.writeUTF(strFromClient);
                    break;
                }
                System.out.println("Client: " + strFromClient);
            }
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        System.out.println("Клиент завершил соединение");
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Сервер завершил работу");
        System.exit(0);
    }

    public static void sendMessage(String message) {
        if (!message.isEmpty()) {
            try {
                out.writeUTF(message);
                System.out.println("You:" + message);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainServer());

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.nextLine();
            sendMessage(message);
        }
    }

}

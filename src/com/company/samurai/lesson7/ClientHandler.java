package com.company.samurai.lesson7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public String getName() {
        return name;
    }

    private String name;

    public ClientHandler(MyServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
        this.name = "";
        try {
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    authenticate();
                    readMsg();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException ex) {
            throw new RuntimeException("Client creation error");
        }
    }

    private void authenticate() throws IOException {
        while (true) {
            if (in.available() > 0) {
                String str = in.readUTF();
                if (str.startsWith("/auth")) {
                    String[] parts = str.split("\\s");
                    String nick = server.getAuthService().getNickByLoginAndPwd(parts[1], parts[2]);
                    if (nick != null) {
                        if (!server.isNickLogged(nick)) {
                            System.out.println(nick + " logged into chat");
                            name = nick;
                            sendMsg("/authOk " + nick);
                            server.broadcast(nick + " is in chat");
                            server.subscribe(this);
                            return;
                        } else {
                            System.out.println("User " + nick + " tried to re-enter");
                            sendMsg("User already logged in");
                        }
                    } else {
                        System.out.println("Wrong login/password");
                        sendMsg("Incorrect login attempted");
                    }
                }
            }
        }
    }

    public void sendMsg(String s) {
        try {
            out.writeUTF(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.unsubscribe(this);
        server.broadcast("User " + name + "left");
    }

    private void readMsg() throws IOException {
        while (true) {
            if (in.available() > 0) {
                String message = in.readUTF();
                System.out.println("From " + name + ": " + message);
                if (message.equals("/end")) {
                    return;
                }
                if (message.startsWith("/w ")) {
                    String[] parts = message.split("\\s");
                    server.sendDirect(parts[1], name + ": " + parts[2]);
                } else server.broadcast(name + ": " + message);

            }
        }
    }
}

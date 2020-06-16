package com.company.samurai.lesson7;

import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private class User {
        private String nick;
        private String pass;
        private String login;

        public User(String nick, String pass, String login) {
            this.nick = nick;
            this.pass = pass;
            this.login = login;
        }
    }

    private List<User> userList;

    public AuthService() {
        userList = new ArrayList<>();
        userList.add(new User("nick1", "pass1", "login1"));
        userList.add(new User("nick2", "pass2", "login2"));
        userList.add(new User("nick3", "pass3", "login3"));
    }


    public void start() {
        System.out.println("Authentication service started");
    }

    public void stop() {
        System.out.println("Authentication service stopped");
    }

    public String getNickByLoginAndPwd(String login, String pass) {
        for (User user : userList) {
            if (user.login.equals(login) && user.pass.equals(pass)) {
                return user.nick;
            }
        }
        return null;
    }
}

package ru.rakhimova.javacore.entity;

public class User {
    private String login;
    private int hashPassword;
    private String nick;

    public User(String login, int password, String nick) {
        this.login = login;
        this.hashPassword = password;
        this.nick = nick;
    }

    public String getNick(){
        return nick;
    }

    public String getLogin(){
        return login;
    }

    public int getHashPassword() {
        return hashPassword;
    }
}

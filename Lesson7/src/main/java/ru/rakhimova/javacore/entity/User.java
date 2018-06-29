package ru.rakhimova.javacore.entity;

public class User {
    private String login;
    private String pass;
    private String nick;

    public User(String login, String pass, String nick) {
        this.login = login;
        this.pass = pass;
        this.nick = nick;
    }

    public String getNick(){
        return nick;
    }

    public String getLogin(){
        return login;
    }

}

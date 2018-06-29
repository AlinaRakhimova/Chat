package ru.rakhimova.javacore.server;

public interface AuthService {
    void start();
    String getNickByLogin(String login);
    void stop();
}

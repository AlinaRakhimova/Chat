package ru.rakhimova.javacore.server;

public interface AuthService {
    void start();
    String getNickByLoginPassword(String login, int hashPassword);
    void stop();
}

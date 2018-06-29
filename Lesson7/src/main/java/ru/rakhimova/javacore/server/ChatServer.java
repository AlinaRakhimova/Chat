package ru.rakhimova.javacore.server;

import ru.rakhimova.javacore.base.BaseAuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.logging.Logger;

public class ChatServer {

    private final int SERVER_PORT = 1111;
    private static final Logger LOGGER = Logger.getLogger(ChatServer.class.getSimpleName());
    private static ServerSocket serverSocket = null;
    private static Socket socket = null;
    private Set<ClientHandler> clients;
    private AuthService authService;

    public void startServer(){
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            authService = new BaseAuthService();
            authService.start();
            clients = new HashSet<>();
            System.out.println("Server running, waiting for connection ...");
            while (!serverSocket.isClosed()) {
                socket = serverSocket.accept();
                System.out.println("Client connected");
                ClientHandler newClient = new ClientHandler(this, socket);
                subscribe(newClient);
            }
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) return true;
        }
        return false;
    }

    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public synchronized void unsubscribe(ClientHandler o) {
        clients.remove(o);
    }

    public synchronized void subscribe(ClientHandler o) {
        clients.add(o);
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void sendMsgByUser(String userFrom, String user, String msg) {
        for (ClientHandler o : clients) {
            if (user.equals(o.getName())) o.sendMsg(userFrom + ": " + msg);
        }
    }
}

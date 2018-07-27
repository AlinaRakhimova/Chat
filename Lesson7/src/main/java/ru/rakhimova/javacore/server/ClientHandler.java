package ru.rakhimova.javacore.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

import static java.lang.Thread.*;

public class ClientHandler {

    private static final String END = "/end";
    private static final String AUTHOK = "/authok";
    private static final String AUTH = "/auth";
    private static final String TOUSER = "/w";
    private static final String SIGN_UP = "/signUp";
    private static final int TIMEOUT_SEC = 100;
    private static final Logger LOGGER = Logger.getLogger(ClientHandler.class.getSimpleName());
    private ChatServer chatServer;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private boolean authorizationOk = false;

    public ClientHandler(ChatServer chatServer, Socket socket) {
        try {
            this.chatServer = chatServer;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";
            Thread threadAuthorization = new Thread(new RunnableClassAuthorization());
            threadAuthorization.start();
            Thread threadRead = new Thread(new RunnableClassReadMessage());
            threadRead.start();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void authorization() {
        while (!authorizationOk && !socket.isClosed()) { // цикл авторизации
            String str = null;
            try {
                if (in.available() > 0) {
                    str = in.readUTF();
                    if (str.startsWith(AUTH)) checkNick(str);
                    if (str.startsWith(SIGN_UP)) signUpServer(str);
                }
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    public void signUpServer(String str){
        String[] parts = str.split(" ");
        String login = parts[1];
        String password = parts[2];
        String nick = parts[3];
        System.out.println("signUpServer");
        if (!chatServer.isNickBusy(nick)) {
            System.out.println("Nick is not busy");
            if(chatServer.getAuthServiceDB().addAccount(login, password, nick)) {
                System.out.println("Sign UP OK");
                sendMsg(TOUSER + " " + login + " " + "Sign UP OK");
                checkNick(AUTH + " " + login + " " + password);
            }

        }
    }

    public void checkNick(String str) {
        String[] parts = str.split(" ");
        String nick = chatServer.getAuthServiceDB().getNickByLoginPassword(parts[1], parts[2].hashCode());
        System.out.println("Nick: " + nick);
        if (nick != null) {
            if (!chatServer.isNickBusy(nick)) {
                System.out.println("Nick is not busy in checkNick()");
                sendMsg(AUTHOK + " " + nick);
                name = nick;
                chatServer.broadcastMsg(nick + " went into the chat");
                chatServer.subscribe(this);
                authorizationOk = true;
            } else sendMsg("Account already in use");
        } else {
            sendMsg("Incorrect login / password");
        }
    }

    public void readMsg() {
        try {
            System.out.println(authorizationOk);
          //  sleep(TIMEOUT_SEC * 1000);
         //   if (authorizationOk) {
                while (!socket.isClosed()) { // цикл получения сообщений
                    String str = null;
                    if (in.available() > 0) str = in.readUTF();
                    System.out.println("readMsg() " + str);
                    if (str != null) {
                        if (str.equals(END)) {
                            authorizationOk = false;
                            //break;
                        }
                        if (str.startsWith(TOUSER)) {
                            String[] messageSplit = str.split(" ", 3);
                            chatServer.sendMsgByUser(this, messageSplit[1], messageSplit[2]);
                        } else chatServer.broadcastMsg(name + ": " + str);
                    }
                }
        //    }
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }

    }

    public void closeClient() {
        try {
            sendMsg("Client disconnected");
            if (!name.isEmpty() || !"".equals(name)) {
                chatServer.broadcastMsg(name + " disconnected from the chat");
                chatServer.unsubscribe(this);
            }
            socket.close();
        } catch (IOException e) {
        }
    }

    class RunnableClassAuthorization implements Runnable {
        @Override
        public void run() {
            authorization();
        }
    }

    class RunnableClassReadMessage implements Runnable {
        @Override
        public void run() {
           // try {
                readMsg();
          //  }
            //finally {
               // closeClient();
           // }
        }
    }

}

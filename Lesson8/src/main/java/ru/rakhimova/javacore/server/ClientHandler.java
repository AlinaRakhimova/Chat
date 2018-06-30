package ru.rakhimova.javacore.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class ClientHandler {

    private static final String END = "/end";
    private static final String AUTHOK = "/authok";
    private static final String AUTH = "/auth";
    private static final String TOUSER ="/w";
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
            Thread thread = new Thread(new RunnableClassAuthorizationAndRead(this));
            thread.start();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
            //System.out.println("Отправлено с сервера: " + msg);
            out.flush();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void authorization(ClientHandler o) {
        while (!authorizationOk) { // цикл авторизации
            String str = null;
            try {
                if (in.available() > 0) {
                    str = in.readUTF();
                    //System.out.println("Чтение при авторизации"+ str);
                    if (str.startsWith(AUTH)) checkNick(str, o);
                }
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    public void checkNick(String str, ClientHandler o) {
        String[] parts = str.split(" ");
        String nick = chatServer.getAuthService().getNickByLoginPassword(parts[1], parts[2].hashCode());
        if (nick != null) {
            if (!chatServer.isNickBusy(nick)) {
                sendMsg(AUTHOK + " " + nick);
                name = nick;
                chatServer.broadcastMsg(nick + " went into the chat");
                chatServer.subscribe(o);
                authorizationOk = true;
            } else sendMsg("Account already in use");
        } else {
            sendMsg("Incorrect login / password");
        }
    }

    public void readMsg() {
        while (authorizationOk) { // цикл получения сообщений
            // System.out.println("Началось чтение на сервере");
            String str = null;
            try {
                //System.out.println("Продолжение чтения на сервере");
                if (in.available() > 0) str = in.readUTF();
                //System.out.println("Прочитано на сервере: " + str);
            } catch (IOException e) {
                //System.out.println("Не читается на сервере");
                LOGGER.severe(e.getMessage());
            }
            if (str != null) {
                if (str.equals(END)) break;
                if (str.startsWith(TOUSER)) {
                    String[] messageSplit = str.split(" ", 3);
                    chatServer.sendMsgByUser(this, messageSplit[1], messageSplit[2]);
                } else chatServer.broadcastMsg(name + ": " + str);
            }
        }
    }

    class RunnableClassAuthorizationAndRead implements Runnable {

        ClientHandler o;

        RunnableClassAuthorizationAndRead(ClientHandler o) {
            this.o = o;
        }

        @Override
        public void run() {
            try {
                authorization(o);
                // System.out.println("Авторизация прошла. Начинаем чтение сообщений");
                readMsg();
                //  System.out.println("Чтение сообщений прошло");
            } finally {
                chatServer.unsubscribe(o);
                System.out.println("Выход из чата: " + name);
                if (!name.isEmpty()) {
                    chatServer.broadcastMsg(name + " left the chat");
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    LOGGER.severe(e.getMessage());
                }
            }
        }
    }

}


package ru.rakhimova.javacore;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {

    static ServerSocket serv = null;
    static Socket sock = null;
    final String AUTHOR = "Server";
    private final int SERVER_PORT = 8189;
    Scanner in = null;
    PrintWriter out = null;
    private static Scanner outScanner = new Scanner(System.in);

    public ChatServer() {
        try {
            serv = new ServerSocket(SERVER_PORT);
            System.out.println("Сервер запущен, ожидаем подключения...");
            sock = serv.accept();
            System.out.println("Клиент подключился");
            in = new Scanner(sock.getInputStream());
            out = new PrintWriter(sock.getOutputStream());
        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
        } finally {
            try {
                serv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage() {
        while (!sock.isOutputShutdown()) {
            if (outScanner.hasNext()) {
                String messageOut = outScanner.nextLine();
                if (!messageOut.isEmpty()) {
                    out.println(AUTHOR + ": " + messageOut);
                    out.flush();
                }
            }
        }
    }

    public void readMessage() {
        while (!sock.isOutputShutdown()) {
            if (in.hasNext()) {
                String messageIn = in.nextLine();
                if (messageIn.equals("end")) break;
                System.out.println(messageIn);
            }
        }
    }

}
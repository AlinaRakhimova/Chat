/**
 * JavaCore. Level 1. Lesson 6. Example of homework
 *
 * @author Alina Rakhimova
 * @version dated June 24, 2018
 * @link https://github.com/AlinaRakhimova
 */

package ru.rakhimova.javacore;

public class AppServer {

    public static void main(String[] args) {
        final ChatServer chatServer = new ChatServer();
        Thread threadRead = new Thread(new Runnable() {
            @Override
            public void run() {
                chatServer.readMessage();
            }
        });
        threadRead.start();

        Thread threadSend = new Thread(new Runnable() {
            @Override
            public void run() {
                chatServer.sendMessage();
            }
        });
        threadSend.start();
    }

}

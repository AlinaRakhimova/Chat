/**
 * JavaCore. Level 1. Lesson 7. Example of homework
 *
 * @author Alina Rakhimova
 * @version dated June 27, 2018
 * @link https://github.com/AlinaRakhimova
 */

package ru.rakhimova.javacore.server;

public class AppServer {

    public static void main(String[] args) {
        final ChatServer chatServer = new ChatServer();
        chatServer.startServer();
    }

}

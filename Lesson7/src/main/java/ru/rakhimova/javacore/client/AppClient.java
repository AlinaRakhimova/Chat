
package ru.rakhimova.javacore.client;

import javax.swing.*;
import java.util.logging.Logger;

public class AppClient {
    private static final Logger LOGGER = Logger.getLogger(AppClient.class.getSimpleName());
    public static void main(String[] args) {
        ChatClient dialogChat = new ChatClient();
        dialogChat.startClient();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(dialogChat);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        dialogChat.setLocationRelativeTo(null);
        dialogChat.pack();
        dialogChat.setVisible(true);
        System.exit(0);

    }
}

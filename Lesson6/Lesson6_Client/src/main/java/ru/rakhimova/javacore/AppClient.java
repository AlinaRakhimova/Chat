/**
 * JavaCore. Level 1. Lesson 6. Example of homework
 *
 * @author Alina Rakhimova
 * @version dated June 24, 2018
 * @link https://github.com/AlinaRakhimova
 */

package ru.rakhimova.javacore;

import javax.swing.*;

public class AppClient {
    public static void main(String[] args) {
        ChatClient dialogChat = new ChatClient();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(dialogChat);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        dialogChat.setLocationRelativeTo(null);
        dialogChat.pack();
        dialogChat.setVisible(true);
        System.exit(0);

    }
}

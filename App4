/**
 * JavaCore. Level 1. Lesson 4. Example of homework
 *
 * @author Alina Rakhimova
 * @version dated June 09, 2018
 * @link https://github.com/AlinaRakhimova
 */

package ru.rakhimova.javacore;

import javax.swing.*;

public class App
{
    public static void main( String[] args ) {
        ChatWindow dialogChat = new ChatWindow();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(dialogChat);
        } catch (Exception ex) {ex.printStackTrace();}
        dialogChat.setLocationRelativeTo(null);
        dialogChat.pack();
        dialogChat.setVisible(true);
        System.exit(0);

    }
}

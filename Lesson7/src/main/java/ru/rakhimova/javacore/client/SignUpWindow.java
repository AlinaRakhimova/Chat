package ru.rakhimova.javacore.client;

import javax.swing.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class SignUpWindow extends JDialog {
    private static final String SIGN_UP = "/signUp";
    private static final Logger LOGGER = Logger.getLogger(SignUpWindow.class.getSimpleName());
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 9090;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldLogin, textFieldPassword, textFieldNick;
    private JLabel lableLogin;
    private JLabel lablePassword;
    private JLabel lableNick;
    private DataOutputStream out;
    private Socket socketClient;

    public void startSignUpWindow() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        connectionToServer();
    }

    public void connectionToServer() {
        try {
            socketClient = new Socket(SERVER_ADDR, SERVER_PORT);
          //  in = new DataInputStream(socketClient.getInputStream());
            out = new DataOutputStream(socketClient.getOutputStream());
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    private void onOK() {
        String strSignUp = SIGN_UP + " " + textFieldLogin.getText() + " " + textFieldPassword.getText() + " " + textFieldNick.getText();
        try {
            out.writeUTF(strSignUp);
            out.flush();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        SignUpWindow dialog = new SignUpWindow();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

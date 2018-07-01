package ru.rakhimova.javacore.client;

import javax.swing.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class ChatClient extends JDialog {
    private static final String SERVER_ADDR = "localhost";
    private static final String END = "/end";
    private static final String AUTH = "/auth";
    private static final int SERVER_PORT = 9090;
    private static final Logger LOGGER = Logger.getLogger(ChatClient.class.getSimpleName());
    private JPanel contentPane;
    private JButton buttonSend;
    private JTextArea textAreaMessage;
    private JTextField textMessage;
    private JScrollPane scrollTextArea;
    private JPanel panelAuthorization;
    private JTextField textFieldLogin;
    private JTextField textFieldPassword;
    private JLabel labelLogin;
    private JLabel labelPassword;
    private JButton signInButton;
    private Socket socketClient;
    private DataInputStream in;
    private DataOutputStream out;

    public void startClient() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSend);

        buttonSend.addActionListener(e -> sendMsg());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        signInButton.addActionListener(e -> onAuthClick());

        connectionToServer();

        Thread t = new Thread(new RunnableClassChatClient());
        t.start();
    }

    public void connectionToServer() {
        try {
            socketClient = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new DataInputStream(socketClient.getInputStream());
            out = new DataOutputStream(socketClient.getOutputStream());
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    private void onCancel() {
        try {
            out.writeUTF(END);
            out.flush();
            dispose();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void sendMsg() {
        try {
            boolean checkTextMessage = textMessage.getText().trim().isEmpty();
            if (!checkTextMessage) {
                out.writeUTF(textMessage.getText());
                out.flush();
                textMessage.setText("");
            }
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void onAuthClick() {
        try {
            out.writeUTF(AUTH + " " + textFieldLogin.getText() + " " + textFieldPassword.getText());
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }

    class RunnableClassChatClient implements Runnable {

        @Override
        public void run() {
            try {
                while (!socketClient.isClosed()) {
                    String str = null;
                    if (in.available() > 0) str = in.readUTF();
                    if (str != null) textAreaMessage.append(str + "\n");
                }
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

}

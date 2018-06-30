package ru.rakhimova.javacore.client;

import javax.swing.*;
import java.awt.*;
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
    private static final int SERVER_PORT = 1111;
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

        try {
            socketClient = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new DataInputStream(socketClient.getInputStream());
            out = new DataOutputStream(socketClient.getOutputStream());
            //setAuthorized(false);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        Thread t = new Thread(new RunnableClassChatClient());
        t.start();

        signInButton.addActionListener(e -> onAuthClick());
    }

    private void onCancel() {
        dispose();
    }

    public void sendMsg() {
        try {
            boolean checkTextMessage = textMessage.getText().trim().isEmpty();
            if (!checkTextMessage) {
                out.writeUTF(textMessage.getText());
                out.flush();
                textMessage.setText("");
                //System.out.println("Отправка с клиента удалась");
            }
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void onAuthClick() {
        try {
            out.writeUTF(AUTH + " " + textFieldLogin.getText() + " " + textFieldPassword.getText());
           // textFieldLogin.setText("");
           // textFieldPassword.setText("");
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }

    class RunnableClassChatClient implements Runnable {

        @Override
        public void run() {
            try {
                while (!socketClient.isOutputShutdown()) {
                    String str = in.readUTF();
                    //System.out.println("Прочитано на клиенте: " + str);
                    if (str.equals(END)) {
                        break;
                    }
                    textAreaMessage.append(str + "\n");
                }
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            } finally {
                try {
                    socketClient.close();
                } catch (IOException e) {
                    LOGGER.severe(e.getMessage());
                }
            }
        }
    }

}

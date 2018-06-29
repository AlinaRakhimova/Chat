package ru.rakhimova.javacore;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient extends JDialog {
    private JPanel contentPane;
    private JButton buttonSend;
    private JTextArea textAreaMessage;
    private JTextField textMessage;
    private JScrollPane scrollTextArea;
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;
    private Socket sock;
    private Scanner in;
    private PrintWriter out;
    final String AUTHOR = "Client";

    public ChatClient() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSend);

        buttonSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSend();
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

        try {
            sock = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new Scanner(sock.getInputStream());
            out = new PrintWriter(sock.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(new RunnableClassChatClient());
        thread.start();

    }

    private void onCancel() {
        dispose();
    }

    public void onSend() {
        if (!textMessage.getText().trim().isEmpty()) {
            out.println(AUTHOR + ": " + textMessage.getText());
            out.flush();
            textMessage.setText("");
        }
    }

    class RunnableClassChatClient implements Runnable {

        @Override
        public void run() {
            try {
                while (!sock.isOutputShutdown()) {
                    if (in.hasNext()) {
                        String w = in.nextLine();
                        if (w.equalsIgnoreCase("end")) break;
                        textAreaMessage.append(w);
                        textAreaMessage.append("\n");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

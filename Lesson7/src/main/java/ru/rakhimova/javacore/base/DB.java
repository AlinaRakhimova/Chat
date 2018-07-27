package ru.rakhimova.javacore.base;

import org.sqlite.JDBC;
import ru.rakhimova.javacore.entity.User;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class DB {

    String DB_PATH = "src/DB_Accounts.db";
    private static final int QUANTITY_STUDENTS = 5;
    private static final String ADD_STUDENT = "/add";
    private static final String CHANGE_STUDENT = "/change";
    private static final String DELETE_STUDENT = "/delete";
    private static final String END = "/end";
    private static final String SELECT = "/select";
    private static final Logger LOGGER = Logger.getLogger(DB.class.getSimpleName());
    public static Connection connection;
    public static PreparedStatement preparedStatement;
    public static ResultSet resultSet;
    private Scanner scanner;

    public void start() {
        connectionDB(DB_PATH);
        createDB();
    }

    public void connectionDB(String DB_PATH) {
        connection = null;
        try {
            connection = DriverManager.getConnection(JDBC.PREFIX + DB_PATH);
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void createDB() {
        try {
            preparedStatement = connection.prepareStatement("CREATE TABLE if not exists accounts (id INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, password TEXT, nick TEXT);");
            preparedStatement.execute();
            System.out.println("Table created");
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void writeDB() {
        clearTable();
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO students (surname, mark) VALUES (?,?)");
            for (int i = 1; i <= QUANTITY_STUDENTS; i++) {
                preparedStatement.setString(1, "Surname" + i);
                preparedStatement.setDouble(2, Math.random() * 5);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
        System.out.println("Table is full");

    }

    public void selectAll() {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM accounts");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String nick = resultSet.getString("nick");
                System.out.println(login + " " + nick + " " + password);
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public boolean addAccount(String login, String password, String nick) {
        boolean addOK = false;
        if (checkLogin(login)) {
            try {
                preparedStatement = connection.prepareStatement("INSERT INTO accounts (login, password, nick) VALUES (?,?,?)");
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, nick);

                int i = preparedStatement.executeUpdate();
                if (i > 0) addOK = true;
            } catch (SQLException e) {
                LOGGER.severe(e.getMessage());
                System.out.println("Not added");
            }
        }
        return addOK;
    }

    public boolean checkLogin(String login) {
        boolean loginClear = false;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE login = ?");
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) loginClear = true;
            else loginClear = false;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
        return loginClear;
    }

    public void changeNickPassword(String login, String password, String nick) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE accounts SET password = ? AND nick = ? WHERE login = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, nick);
            preparedStatement.setString(3, login);
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                System.out.println("Password changed");
            } else System.out.println("No changed");
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }

    }

    public void deleteAccount(String login) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM accounts WHERE login = ?");
            preparedStatement.setString(1, login);
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                System.out.println("Account deleted");
            } else System.out.println("No deleted");
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }

    }

    public String getNickByLoginPassword(String login, int hashPassword) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE login = ?");
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int hashPasswordDB = resultSet.getString("password").hashCode();
                String nick = resultSet.getString("nick");
                if (hashPasswordDB == hashPassword) return nick;
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
        return null;
    }

    public static void clearTable() {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM accounts");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void closeDB() {
        try {
            connection.close();
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }

    }

}

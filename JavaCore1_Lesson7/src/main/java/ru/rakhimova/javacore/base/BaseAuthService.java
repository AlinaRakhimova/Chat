package ru.rakhimova.javacore.base;

import ru.rakhimova.javacore.entity.User;
import ru.rakhimova.javacore.server.AuthService;

import java.util.HashSet;
import java.util.Set;

public class BaseAuthService implements AuthService {
    private Set<User> entries;

    @Override
    public void start() { }
    @Override
    public void stop() { }

    public BaseAuthService() {
        entries = new HashSet<>();
        entries.add(new User( "Mike","pass1", "Mike"));
        entries.add(new User( "Bob","pass2", "Bob"));
        entries.add(new User( "Megan","pass3", "Megan"));
    }
    @Override
    public String getNickByLogin(String login) {
        for (User user : entries) {
            String entryLogin = user.getLogin();
            if (entryLogin.equals(login)) return user.getNick();
        }
        return null;
    }
}


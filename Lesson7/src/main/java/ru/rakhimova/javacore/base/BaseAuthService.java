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
        final int pass1 = "pass1".hashCode();
        final int pass2 = "pass2".hashCode();
        final int pass3 = "pass3".hashCode();
        entries.add(new User( "Mike",pass1, "Mike"));
        entries.add(new User( "Bob",pass2, "Bob"));
        entries.add(new User( "Megan",pass3, "Megan"));
    }
    @Override
    public String getNickByLoginPassword(String login, int hashPassword) {
        for (User user : entries) {
            String entryLogin = user.getLogin();
            int entryPassword = user.getHashPassword();
            if (entryLogin.equals(login) && (entryPassword==(hashPassword))) return user.getNick();
        }
        return null;
    }
}


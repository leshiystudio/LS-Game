package ru.l8s.LSFightGame.authentication.impl;

import org.springframework.stereotype.Component;
import ru.l8s.LSFightGame.authentication.api.IUser;

@Component
public class User implements IUser{
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    private String name;
    private String password;

    public String getLogin() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
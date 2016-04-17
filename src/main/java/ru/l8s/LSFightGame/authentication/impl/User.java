package ru.l8s.LSFightGame.authentication.impl;

import org.springframework.stereotype.Component;
import ru.l8s.LSFightGame.authentication.api.IUser;

@Component
public class User implements IUser{

    private String name;
    private String password;

    public String getName() {
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
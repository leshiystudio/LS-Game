package ru.l8s.LSFightGame.authentication.impl;

import ru.l8s.LSFightGame.authentication.api.IUserService;

/**
 * Created by LS on 17.04.2016.
 */
public class UserService implements IUserService {
    @Override
    public User getUser(String login) {
        User user = new User(login,"7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
        return user;
    }
}

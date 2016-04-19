package ru.l8s.LSFightGame.authentication.impl;

import ru.l8s.LSFightGame.authentication.api.IUserService;

/**
 * Created by LS on 17.04.2016.
 */
public class UserService implements IUserService {
    @Override
    public User getUser(String login) {
        return new User(login,"1234");
    }
}

package ru.l8s.LSFightGame.authentication.api;

import ru.l8s.LSFightGame.authentication.impl.User;

/**
 * Created by LS on 17.04.2016.
 */
public interface IUserService {
    User getUser(String login);
}

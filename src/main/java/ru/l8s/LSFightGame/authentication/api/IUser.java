package ru.l8s.LSFightGame.authentication.api;

/**
 * Created by LS on 17.04.2016.
 */
public interface IUser {
    public String getLogin();
    public void setName(String name);
    public String getPassword();
    public void setPassword(String password);
}

package ru.l8s.LSFightGame.authentication.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.l8s.LSFightGame.authentication.api.IUser;
import ru.l8s.LSFightGame.authentication.api.UserRoleEnum;

import java.io.Serializable;
import java.util.Collection;

@Component
public class User implements UserDetails, Serializable{
    private int id;
    private String username;
    private String password;

    transient private UserRoleEnum ROLE=UserRoleEnum.ROLE_USER;

    public User(String name, String password) {
        super();
        this.username = name;
        this.password = password;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User setUsername(String name) {
        this.username = name; return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() { return this.username; }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getROLE() {
        return ROLE.name();
    }

    public void setROLE(UserRoleEnum ROLE) {
        this.ROLE = ROLE;
    }
}
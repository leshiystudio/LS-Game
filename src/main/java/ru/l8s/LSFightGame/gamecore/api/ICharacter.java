package ru.l8s.LSFightGame.gamecore.api;

/**
 * Created by LS on 17.04.2016.
 */
public interface ICharacter {
    int health=100;
    int armor=100;
    boolean fightTo(ICharacter enemy);
}


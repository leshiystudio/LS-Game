package ru.l8s.LSFightGame.gamecore.api;

/**
 * Created by LS on 19.04.2016.
 */
public interface IWeapon {
    int getDamage();
    void setDamage(int damage);
}

abstract class AbsstractWeapon{
    int damage=100;

}

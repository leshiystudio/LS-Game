package ru.l8s.LSFightGame.gamecore.impl;

import ru.l8s.LSFightGame.gamecore.api.ICharacter;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by LS on 17.04.2016.
 */
public class Character implements  Serializable {
    private int id;
    private String name;
    private int health;
    private int armor;
    private int speed;
    private int power;
    private int dexterity;

    private int countWin;
    private int countLoss;

    transient private int user_id;

    public static int allSkillPoints = 10;


    public Character() {
    }

    public Character(int health, int armor, int speed, int power, int dexterity) {
        this.health = health;
        this.armor = armor;
        this.speed = speed;
        this.power = power;
        this.dexterity = dexterity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getAllSkillPoints() {
        return allSkillPoints;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    private void hit(Character enemy){
        Random r =new Random();

        int damage = ( (this.power +  (r.nextInt(this.dexterity==0?1:this.dexterity)-r.nextInt(enemy.dexterity==0?1:enemy.dexterity))   )* (r.nextInt(this.speed==0?1:this.speed)+1));
        int dArmor = r.nextInt(enemy.armor+1);
        int damageOnHealth = (damage/((dArmor==0)?1:dArmor));
        enemy.health-=damageOnHealth<0?0:damageOnHealth;
        enemy.health=enemy.health<0?0:enemy.health;
        enemy.armor-=(damage<0||enemy.armor<1)?0:damage;
        enemy.armor=enemy.armor<0?0:enemy.armor;
    }

    public boolean fightTo(Character enemy) {
        int startHealthUser = this.health;
        int startHelathEnemy = enemy.health;
        for(int i=1;i<=Character.allSkillPoints;i++){
            if(this.health>0 && enemy.health>0){
                if(i%2 == 0){
                    // us hit
                    this.hit(enemy);
                }else{
                    //enemy hit
                    enemy.hit(this);
                }
            }
        }
        int deltaHealthUser = startHealthUser - this.health;
        int deltaHealthEnemy = startHelathEnemy - enemy.health;

        return  deltaHealthUser < deltaHealthEnemy;
    }

    public int getCountWin() {
        return countWin;
    }

    public int getCountLoss() {
        return countLoss;
    }

    public void setCountWin(int countWin) {
        this.countWin = countWin;
    }

    public void setCountLoss(int countLoss) {
        this.countLoss = countLoss;
    }
}


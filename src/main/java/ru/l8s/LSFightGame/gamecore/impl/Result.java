package ru.l8s.LSFightGame.gamecore.impl;

import java.io.Serializable;

/**
 * Created by LS on 22.04.2016.
 */
public class Result implements Serializable {
    private int addition;
    private int subtraction;
    private int multiplication;

    public Result() {
    }

    public Result(int addition, int subtraction, int multiplication) {
        this.addition = addition;
        this.subtraction = subtraction;
        this.multiplication = multiplication;
    }

    public int getAddition() { return addition; }
    public void setAddition(int addition) { this.addition = addition; }
    public int getSubtraction() { return subtraction; }
    public void setSubtraction(int subtraction) { this.subtraction = subtraction; }
    public int getMultiplication() { return multiplication; }
    public void setMultiplication(int multiplication) { this.multiplication = multiplication; }

}

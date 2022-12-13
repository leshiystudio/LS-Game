package ru.l8s.LSFightGame.gamecore.impl;

import java.io.Serializable;

/**
 * Created by LS on 22.04.2016.
 */
public class Request implements Serializable {
    private int left;
    private int right;

    public int getLeft() {return left;}
    public void setLeft(int left) {this.left = left;}
    public int getRight() {return right;}
    public void setRight(int right) {this.right = right;}
}
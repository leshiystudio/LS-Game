package ru.l8s.LSFightGame.controller;

/**
 * Created by LS on 22.04.2016.
 */
public class pageInfo{
    private String title="LS-Game";
    private String lsng="ru";
    private String footer="@LS-Game - Алексей Крюков";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLsng() {
        return lsng;
    }

    public void setLsng(String lsng) {
        this.lsng = lsng;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
}

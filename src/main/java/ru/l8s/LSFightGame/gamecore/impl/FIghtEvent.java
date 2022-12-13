package ru.l8s.LSFightGame.gamecore.impl;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by LS on 20.05.2016.
 */
public class FIghtEvent implements Serializable {
    private Character winner;
    private Character loser;
    private Timestamp date;

    public Character getWinner() {
        return winner;
    }

    public void setWinner(Character winner) {
        this.winner = winner;
    }

    public Character getLoser() {
        return loser;
    }

    public void setLoser(Character looser) {
        this.loser = looser;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}

package ru.l8s.LSFightGame.gamecore.impl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LS on 14.05.2016.
 */
public class ResponsePageCharacters implements Serializable {
    List<Character> characterList;
    int backPage;
    int nextPage;
    int allPage;

    public ResponsePageCharacters() {
    }

    public ResponsePageCharacters(List<Character> characterList, int backPage, int nextPage, int allPage) {
        this.characterList = characterList;
        this.backPage = backPage;
        this.nextPage = nextPage;
        this.allPage = allPage;
    }

    public List<Character> getCharacterList() {
        return characterList;
    }

    public void setCharacterList(List<Character> characterList) {
        this.characterList = characterList;
    }

    public int getBackPage() {
        return backPage;
    }

    public void setBackPage(int backPage) {
        this.backPage = backPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getAllPage() {
        return allPage;
    }

    public void setAllPage(int allPage) {
        this.allPage = allPage;
    }
}

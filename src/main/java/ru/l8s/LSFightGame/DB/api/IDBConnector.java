package ru.l8s.LSFightGame.DB.api;

import java.util.List;

/**
 * Created by LS on 17.04.2016.
 */
public interface IDBConnector<T> {
    int insert(T val);
    void update(T val);
    void delete(Integer id);
    void delete(T val);
    T get(Integer id);
    T get(String name);
    List<T> getAll();

}


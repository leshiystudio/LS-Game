package ru.l8s.LSFightGame.DB.api;

import org.springframework.jdbc.core.metadata.PostgresCallMetaDataProvider;

import java.util.List;

/**
 * Created by LS on 17.04.2016.
 */
public interface IDBConnector<T> {
    void insert(T data);
    void update(T data);
    void delete(T data);
    T get(String val);
    T get(Integer id);
    List<T> getAll();
}


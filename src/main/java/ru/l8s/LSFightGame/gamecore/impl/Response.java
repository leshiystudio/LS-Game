package ru.l8s.LSFightGame.gamecore.impl;

import java.io.Serializable;

/**
 * Created by LS on 19.05.2016.
 */
public class Response<T> implements Serializable {
    private T data;
    private int error;
    private String msg;

    public Response() {
    }

    public Response(T data, int error, String msg) {
        this.data = data;
        this.error = error;
        this.msg = msg;
    }

    public int getError() {
        return error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

package com.allen.learningbootautoconfigure.web;

public class RestMessage<T> {

    private int status;
    private String msg;
    private T data;

    public RestMessage(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static RestMessage fail(String msg) {
        return new RestMessage<Object>(1, msg, null);
    }

    public static <E> RestMessage<E> ok(E data) {
        return new RestMessage<E>(0, "success", data);
    }

    public static RestMessage ok() {
        return ok(null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

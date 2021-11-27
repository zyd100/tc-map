package com.zyd.tcmap.dto;

public class Result<T>  {
    private T data;
    private String message;
    private int code;

    public Result(T data, int code, String message) {
        setCode(code);
        setData(data);
        setMessage(message);
    }



    public static <T> Result<T> response(int code, String message) {
        return new Result<T>(null, code, message);
    }

    public static <T> Result<T> response(T data, int code, String message) {
        return new Result<T>(data, code, message);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}

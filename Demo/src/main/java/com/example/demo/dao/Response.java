package com.example.demo.dao;

import lombok.Data;

/**
 * @Author zhourui
 * @Date 2019/9/29 14:20
 */
@Data
public class Response<T> {

    private boolean success;
    private long code;
    private String message;
    private T data;

    public Response(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Response success() {
        Response response = new Response(200L, "操作成功!");
        response.setSuccess(true);
        return response;
    }

    public static Response fail(long code, String message) {
        Response response = new Response(code, message);
        response.setSuccess(false);
        return response;
    }

}

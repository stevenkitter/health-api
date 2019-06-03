package org.spring.springboot.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public Response(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
package com.hsw.testapp.domain.network.exception;

import java.io.Serializable;

/**
 * @author heshuai
 * created on: 2019-09-24 13:37
 * description:
 */
public class ApiException extends Exception implements Serializable {
    private int code;
    private String msg;


    public ApiException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}

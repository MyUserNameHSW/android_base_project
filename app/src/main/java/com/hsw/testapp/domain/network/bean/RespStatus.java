package com.hsw.testapp.domain.network.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author heshuai
 * created on: 2019-09-24 13:26
 * description:
 */
public class RespStatus {
    @SerializedName("code")
    int code;
    @SerializedName("msg")
    String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}

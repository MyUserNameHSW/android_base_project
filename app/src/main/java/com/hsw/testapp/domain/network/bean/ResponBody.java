package com.hsw.testapp.domain.network.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author heshuai
 * created on: 2019-09-24 13:27
 * description:
 */
public class ResponBody<T> {
    @SerializedName("status")
    RespStatus status;
    @SerializedName("data")
    T result;

    public RespStatus getStatus() {
        return status;
    }

    public void setStatus(RespStatus status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}

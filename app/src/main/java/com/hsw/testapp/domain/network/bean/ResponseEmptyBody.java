package com.hsw.testapp.domain.network.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author heshuai
 * created on: 2019-09-24 13:28
 * description:
 */
public class ResponseEmptyBody {
    @SerializedName("status")
    RespStatus status;

    public RespStatus getStatus() {
        return status;
    }

    public void setStatus(RespStatus status) {
        this.status = status;
    }
}

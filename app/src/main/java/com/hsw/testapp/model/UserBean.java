package com.hsw.testapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author heshuai
 * created on: 2019-09-24 14:34
 * description:
 */
public class UserBean implements Serializable {
    @SerializedName("id")
    String userId;
    @SerializedName("phone")
    String phone;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userId='" + userId + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

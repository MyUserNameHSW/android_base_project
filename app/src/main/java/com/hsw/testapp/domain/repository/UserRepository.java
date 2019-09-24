package com.hsw.testapp.domain.repository;

import com.hsw.testapp.model.UserBean;

import io.reactivex.Observable;

/**
 * @author heshuai
 * created on: 2019-09-24 13:59
 * description:
 */
public interface UserRepository {

    Observable<UserBean> login(String phone , String password);

    Observable<Object> register(String phone,String password);
}

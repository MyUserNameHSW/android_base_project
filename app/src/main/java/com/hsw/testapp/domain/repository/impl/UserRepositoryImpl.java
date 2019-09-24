package com.hsw.testapp.domain.repository.impl;

import com.hsw.testapp.domain.network.ReqService;
import com.hsw.testapp.domain.network.handler.NetworkResultHandler;
import com.hsw.testapp.domain.repository.UserRepository;
import com.hsw.testapp.model.UserBean;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author heshuai
 * created on: 2019-09-24 14:00
 * description:
 */
public class UserRepositoryImpl implements UserRepository {

    private Retrofit retrofit;

    @Inject
    public UserRepositoryImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<UserBean> login(String phone, String password) {
        return getService().login(phone, password).map(NetworkResultHandler.handlerResult());
    }

    @Override
    public Observable<Object> register(String phone, String password) {
        return getService().register(phone, password).map(NetworkResultHandler.handlerEmptyResult());
    }

    private ReqService getService() {
        return retrofit.create(ReqService.class);
    }

}

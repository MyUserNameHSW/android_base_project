package com.hsw.testapp.domain.data;

import com.hsw.testapp.domain.executor.PostExecutionThread;
import com.hsw.testapp.domain.executor.ThreadExecutor;
import com.hsw.testapp.domain.network.MultiUseCase;
import com.hsw.testapp.domain.repository.UserRepository;
import com.hsw.testapp.model.UserBean;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * @author heshuai
 * created on: 2019-09-24 14:32
 * description:
 */
public class UserData extends MultiUseCase {
    @Inject
    UserRepository repository;

    @Inject
    public UserData(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    public void login(DisposableObserver<UserBean> disposableObserver,String phone,String password) {
        execute(disposableObserver,repository.login(phone, password));
    }

    public void register(DisposableObserver<Object> disposableObserver,String phone,String password) {
        execute(disposableObserver,repository.register(phone, password));
    }
}

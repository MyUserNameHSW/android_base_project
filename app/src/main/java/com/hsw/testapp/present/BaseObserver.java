package com.hsw.testapp.present;

import com.hsw.testapp.common.NetworkConstants;
import com.hsw.testapp.domain.network.exception.ApiException;

import io.reactivex.observers.DisposableObserver;

/**
 * @author heshuai
 * created on: 2019-09-24 14:15
 * description:
 */
public abstract class BaseObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable exception) {
        if (exception instanceof ApiException) {
            onError(((ApiException) exception).getCode(),((ApiException) exception).getMsg());
        } else {
            onError(NetworkConstants.STATUS_ERROR,NetworkConstants.ERROR_MSG);
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 请求成功回调
     * @param t
     */
    abstract void onSuccess(T t);

    /**
     * 失败回调
     * @param code
     * @param msg
     */
    abstract void onError(int code,String msg);
}

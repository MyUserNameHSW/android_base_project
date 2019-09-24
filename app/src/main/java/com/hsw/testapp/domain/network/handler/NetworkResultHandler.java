package com.hsw.testapp.domain.network.handler;

import com.hsw.testapp.common.NetworkConstants;
import com.hsw.testapp.domain.network.exception.ApiException;
import com.hsw.testapp.domain.network.bean.ResponBody;
import com.hsw.testapp.domain.network.bean.ResponseEmptyBody;

import io.reactivex.functions.Function;

/**
 * Create by hsw
 * on 2018/8/17.
 */

public class NetworkResultHandler {

    public static <T> Function<ResponBody<T>, T> handlerResult() {
        return responseBody -> {
            if (responseBody.getStatus().getCode() != NetworkConstants.STATUS_SUCCESS) {
                throw new ApiException(responseBody.getStatus().getCode(), responseBody.getStatus().getMsg());
            }
            return responseBody.getResult();
        };
    }

    /**
     * 由于retrofit2 解析null 数据会报异常，所以当返回结果result为空时直接不解析
     */
    public static Function<ResponseEmptyBody, Object> handlerEmptyResult() {
        return responseEmptyBody -> {
            if (responseEmptyBody.getStatus().getCode() != NetworkConstants.STATUS_SUCCESS) {
                throw new ApiException(responseEmptyBody.getStatus().getCode(), responseEmptyBody.getStatus().getMsg());
            }
            return responseEmptyBody;
        };
    }
}

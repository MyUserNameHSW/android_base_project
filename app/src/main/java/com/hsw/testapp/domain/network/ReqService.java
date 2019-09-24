package com.hsw.testapp.domain.network;

import com.hsw.testapp.domain.network.bean.ResponBody;
import com.hsw.testapp.domain.network.bean.ResponseEmptyBody;
import com.hsw.testapp.model.UserBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author heshuai
 * created on: 2019-09-24 13:29
 * description:
 */
public interface ReqService {

    @GET("user/login?_debug=1")
    Observable<ResponBody<UserBean>> login(@Query("phone") String phone, @Query("password") String password);

    @GET("user/register?_debug=1")
    Observable<ResponseEmptyBody> register(@Query("phone") String phone,@Query("password") String password);
}

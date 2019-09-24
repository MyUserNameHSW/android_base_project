package com.hsw.testapp.interactor;

import com.hsw.testapp.model.UserBean;

/**
 * @author heshuai
 * created on: 2019-09-24 14:02
 * description:
 */
public interface LoginView extends LoadDataView {
    /**
     * 登录成功
     * @param userBean
     */
    void loginSuccess(UserBean userBean);

    /**
     * 注册成功
     */
    void registerSuccess();
}

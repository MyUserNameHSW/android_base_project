package com.hsw.testapp.present;

import com.hsw.testapp.domain.data.UserData;
import com.hsw.testapp.interactor.LoginView;
import com.hsw.testapp.model.UserBean;

import javax.inject.Inject;

/**
 * @author heshuai
 * created on: 2019-09-24 14:39
 * description:
 */
public class UserPresent extends BasePresent {
    private UserData userData;

    @Inject
    public UserPresent(UserData userData) {
        this.userData = userData;
    }

    private LoginView loginView;

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(String phone, String password) {
        if (isLoading) {
            return;
        }
        isLoading = true;
        loginView.showLoading();
        userData.login(new LoginObserver(),phone,password);
    }

    public void register(String phone,String password) {
        if (isLoading) {
            return;
        }
        isLoading = true;
        loginView.showLoading();
        userData.register(new RegisterObserver(),phone,password);
    }

    class LoginObserver extends BaseObserver<UserBean> {
        @Override
        void onSuccess(UserBean userBean) {
            isLoading = false;
            loginView.hideLoading();
            loginView.loginSuccess(userBean);
        }

        @Override
        void onError(int code, String msg) {
            isLoading = false;
            loginView.hideLoading();
            loginView.showToast(msg);
        }
    }

    class RegisterObserver extends BaseObserver<Object> {
        @Override
        void onSuccess(Object object) {
            isLoading = false;
            loginView.hideLoading();
            loginView.registerSuccess();
        }

        @Override
        void onError(int code, String msg) {
            isLoading = false;
            loginView.hideLoading();
            loginView.showToast(msg);
        }
    }
    /**
     * 当activity 或 fragment 销毁时取消订阅
     */
    @Override
    public void dispose() {
        if (null != userData) {
            userData.dispose();
        }
    }
}

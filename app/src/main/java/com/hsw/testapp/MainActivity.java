package com.hsw.testapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.hsw.testapp.interactor.LoginView;
import com.hsw.testapp.model.UserBean;
import com.hsw.testapp.present.UserPresent;
import com.hsw.testapp.view.activity.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements LoginView {
    private EditText etPhone, etPassword;
    private TextView tvText;

    @Inject
    UserPresent present;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        present.setLoginView(this);

        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        tvText = findViewById(R.id.tv_text);

        findViewById(R.id.btn_login).setOnClickListener(v -> {
            sendReq(true);
        });

        findViewById(R.id.btn_register).setOnClickListener(v -> {
            sendReq(false);
        });
    }


    private void sendReq(boolean isLogin) {
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            showToast("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast("请输入密码");
            return;
        }

        if (isLogin) {
            present.login(phone, password);
        } else {
            present.register(phone, password);
        }
    }

    @Override
    public void loginSuccess(UserBean userBean) {
        showToast("登录成功");
        tvText.setText(userBean.toString());
    }

    @Override
    public void registerSuccess() {
        showToast("注册成功");
        tvText.setText("注册成功");
    }

    @Override
    protected void initializeInjector() {
        super.initializeInjector();
        getViewComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != present) {
            present.dispose();
        }
    }
}

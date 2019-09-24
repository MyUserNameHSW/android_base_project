package com.hsw.testapp.view.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hsw.testapp.TestApp;
import com.hsw.testapp.domain.dagger.component.DaggerAppComponent;
import com.hsw.testapp.domain.dagger.component.DaggerViewComponent;
import com.hsw.testapp.domain.dagger.component.ViewComponent;
import com.hsw.testapp.domain.dagger.module.ApiModule;
import com.hsw.testapp.domain.dagger.module.ViewModule;
import com.hsw.testapp.interactor.LoadDataView;

/**
 * @author heshuai
 * created on: 2019-09-24 13:35
 * description:
 */
public abstract class BaseActivity extends AppCompatActivity implements LoadDataView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TestApp.getInstance().getAppComponent().inject(this);
        initializeInjector();
    }

    protected void initializeInjector() { }

    protected ViewComponent getViewComponent() {
        return DaggerViewComponent.builder().apiModule(new ApiModule()).viewModule(new ViewModule(this)).appComponent(TestApp.getInstance().getAppComponent()).build();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        // TODO: 2019-09-24 显示loading View
    }

    @Override
    public void hideLoading() {
        // TODO: 2019-09-24 隐藏loading View
    }
}

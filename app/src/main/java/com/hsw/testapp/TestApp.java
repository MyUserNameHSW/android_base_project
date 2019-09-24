package com.hsw.testapp;

import android.app.Application;

import com.hsw.testapp.domain.dagger.component.AppComponent;
import com.hsw.testapp.domain.dagger.component.DaggerAppComponent;
import com.hsw.testapp.domain.dagger.module.AppModule;

/**
 * @author heshuai
 * created on: 2019-09-24 11:55
 * description:
 */
public class TestApp extends Application {
    private static TestApp instance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        instance = this;
    }

    public static TestApp getInstance() {
        return instance;
    }

    private void initializeInjector() {
        this.appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

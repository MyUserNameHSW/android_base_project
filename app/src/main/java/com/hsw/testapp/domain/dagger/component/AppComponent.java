package com.hsw.testapp.domain.dagger.component;

import android.content.Context;

import com.hsw.testapp.TestApp;
import com.hsw.testapp.domain.dagger.module.AppModule;
import com.hsw.testapp.domain.executor.PostExecutionThread;
import com.hsw.testapp.domain.executor.ThreadExecutor;
import com.hsw.testapp.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Create by hsw
 * on 2018/8/16.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(BaseActivity activity);

    TestApp app();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    Retrofit retrofit();
}

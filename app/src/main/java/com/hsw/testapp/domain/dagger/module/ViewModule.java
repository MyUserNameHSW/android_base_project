package com.hsw.testapp.domain.dagger.module;

import com.hsw.testapp.domain.dagger.ViewScope;
import com.hsw.testapp.view.activity.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Create by hsw
 * on 2018/8/16.
 */
@Module
public class ViewModule {
    private BaseActivity activity;

    public ViewModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ViewScope
    BaseActivity activity() {
        return this.activity;
    }


}

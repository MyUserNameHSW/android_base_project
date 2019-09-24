package com.hsw.testapp.domain.dagger.component;

import com.hsw.testapp.MainActivity;
import com.hsw.testapp.domain.dagger.ViewScope;
import com.hsw.testapp.domain.dagger.module.ApiModule;
import com.hsw.testapp.domain.dagger.module.ViewModule;

import dagger.Component;

/**
 * Create by hsw
 * on 2018/8/16.
 */
@ViewScope
@Component(dependencies = AppComponent.class, modules = {ViewModule.class, ApiModule.class})
public interface ViewComponent extends ActivityComponent {

    void inject(MainActivity activity);
}


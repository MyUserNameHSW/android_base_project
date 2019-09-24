package com.hsw.testapp.domain.dagger.component;



import com.hsw.testapp.domain.dagger.ViewScope;
import com.hsw.testapp.domain.dagger.module.ViewModule;
import com.hsw.testapp.view.activity.BaseActivity;

import dagger.Component;

/**
 * Create by hsw
 * on 2018/8/16.
 */
@ViewScope
@Component(dependencies = AppComponent.class,modules = {ViewModule.class})
public interface ActivityComponent {

    BaseActivity activity();
}

package com.hsw.testapp.domain.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Create by hsw
 * on 2018/8/16.
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewScope {}

package com.hsw.testapp.domain.executor;

import io.reactivex.Scheduler;

/**
 * Create by hsw
 * on 2018/8/16.
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}

package com.hsw.testapp.domain.dagger.module;


import com.hsw.testapp.domain.repository.UserRepository;
import com.hsw.testapp.domain.repository.impl.UserRepositoryImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Create by hsw
 * on 2018/8/17.
 */
@Module
public class ApiModule {

    public ApiModule() {
    }

    @Provides
    UserRepository provideUserRepository(UserRepositoryImpl userRepository) {
        return userRepository;
    }
}

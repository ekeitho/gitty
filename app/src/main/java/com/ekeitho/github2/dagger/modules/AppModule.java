package com.ekeitho.github2.dagger.modules;

import com.ekeitho.github2.GithubApplication;
import com.ekeitho.github2.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private GithubApplication githubApplication;

    public AppModule(GithubApplication githubApplication) {
        this.githubApplication = githubApplication;
    }

    @Provides
    @Singleton
    public RxBus provideRxBus() {
        return new RxBus();
    }
}

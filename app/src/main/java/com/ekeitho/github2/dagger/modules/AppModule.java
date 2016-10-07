package com.ekeitho.github2.dagger.modules;

import com.ekeitho.github2.GithubApplication;

import dagger.Module;

@Module
public class AppModule {

    private GithubApplication githubApplication;

    public AppModule(GithubApplication githubApplication) {
        this.githubApplication = githubApplication;
    }
}

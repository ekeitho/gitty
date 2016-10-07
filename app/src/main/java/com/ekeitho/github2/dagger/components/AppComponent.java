package com.ekeitho.github2.dagger.components;

import com.ekeitho.github2.dagger.modules.ActivityModule;
import com.ekeitho.github2.dagger.modules.AppModule;
import com.ekeitho.github2.dagger.modules.GithubModule;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules={AppModule.class, GithubModule.class})
public interface AppComponent {
    ActivityComponent plus(ActivityModule module);
}

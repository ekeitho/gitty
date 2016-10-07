package com.ekeitho.github2.dagger.components;

import com.ekeitho.github2.MainActivity;
import com.ekeitho.github2.dagger.ActivityScope;
import com.ekeitho.github2.dagger.modules.ActivityModule;
import com.ekeitho.github2.dagger.modules.GithubModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules={ActivityModule.class})
public interface ActivityComponent {
    void inject(MainActivity activity);
}

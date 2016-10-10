package com.ekeitho.github2.dagger.modules;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ekeitho.github2.MainActivity;
import com.ekeitho.github2.dagger.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {

    public MainActivity mainActivity;

    public ActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    public LinearLayoutManager provideLayoutManager() {
        return new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    @ActivityScope
    public MainActivity provideMainActivity() {
        return mainActivity;
    }


}

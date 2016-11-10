package com.ekeitho.github2;

import android.app.Application;

import com.ekeitho.github2.dagger.components.AppComponent;
import com.ekeitho.github2.dagger.components.DaggerAppComponent;
import com.ekeitho.github2.dagger.modules.AppModule;

public class GithubApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        super.onCreate();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

package com.ekeitho.github2;

import android.app.Application;

import com.ekeitho.github2.dagger.components.AppComponent;
import com.ekeitho.github2.dagger.components.DaggerAppComponent;
import com.ekeitho.github2.dagger.modules.AppModule;
import com.frogermcs.androiddevmetrics.AndroidDevMetrics;

/**
 * Created by m652315 on 10/5/16.
 */

public class GithubApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        AndroidDevMetrics.initWith(this);
        super.onCreate();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

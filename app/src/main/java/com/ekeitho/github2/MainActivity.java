package com.ekeitho.github2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ekeitho.github2.dagger.components.ActivityComponent;
import com.ekeitho.github2.dagger.modules.ActivityModule;
import com.ekeitho.github2.dagger.modules.GithubModule;
import com.ekeitho.github2.model.GithubRepo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements Provider<ActivityComponent> {

    private static final String TAG = "MainActivity";

    private GithubApplication application;
    private ActivityComponent component;
    
    @Inject GithubModule.GithubEndpoint service;
    @Inject LinearLayoutManager layoutManager;
    @Inject GithubRepoAdapter repoAdapter;
    @Inject RxBus rxBus;
    @BindView(R.id.github_repo_recycler_view) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSetup();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(repoAdapter);

        service.getUserRepos("ekeitho")
                .flatMap(new Func1<List<GithubRepo>, Observable<GithubRepo>>() {
                    @Override
                    public Observable<GithubRepo> call(List<GithubRepo> githubReposes) {
                        return Observable.from(githubReposes);
                    }
                })
                .map(new Func1<GithubRepo, GithubRepo>() {
                    @Override
                    public GithubRepo call(GithubRepo githubRepo) {
                        return githubRepo;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RepoObserver());
    }


    private void initSetup() {
        setContentView(R.layout.activity_main);
        application = (GithubApplication) getApplication();
        component = application.getAppComponent().plus(new ActivityModule(this));
        component.inject(this);
        ButterKnife.bind(this);
    }

    @Override
    public ActivityComponent get() {
        return component;
    }

    private class RepoObserver implements Observer<GithubRepo> {

        @Override
        public void onCompleted() {
            Log.v(TAG, "success!");
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: ", e);
        }

        @Override
        public void onNext(GithubRepo githubRepo) {
            rxBus.send(githubRepo);
        }
    }
}

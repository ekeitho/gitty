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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        application = (GithubApplication) getApplication();

        initSetup();
        callAsyncService();
    }

    private void callAsyncService() {
        String s[] = {"ekeitho", "eleith", "ronyeh"};
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        final Scheduler scheduler = Schedulers.from(executorService);

        Observable.from(s)
                .flatMap(new Func1<String, Observable<GithubRepo>>() {
                    @Override
                    public Observable<GithubRepo> call(String s) {
                        return service.getUserRepos(s)
                                .subscribeOn(scheduler)
                                .flatMap(new Func1<List<GithubRepo>, Observable<GithubRepo>>() {
                                    @Override
                                    public Observable<GithubRepo> call(List<GithubRepo> githubRepos) {
                                        return Observable.from(githubRepos);
                                    }
                                });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RepoObserver(executorService));
    }

    private void initSetup() {
        component = application.getAppComponent().plus(new ActivityModule(this));
        component.inject(this);

        recyclerView.setAdapter(repoAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public ActivityComponent get() {
        return component;
    }

    private class RepoObserver implements Observer<GithubRepo> {

        private ExecutorService service;
        public RepoObserver(ExecutorService service) {
            this.service = service;
        }

        private void safeShutdown() {
            // if the service has not been shut down yet
            if(!this.service.isShutdown()) {
                // shut it down and make sure there aren't any more tasks awaiting to be completed
                List<Runnable> tasks = this.service.shutdownNow();
                if (tasks.size() != 0) {
                    Log.e(TAG, "There should not have been any more tasks!");
                } else {
                    Log.e(TAG, "Good, safe shut down? " + this.service.isTerminated());
                }
            }
        }

        @Override
        public void onCompleted() {
            safeShutdown();
        }

        @Override
        public void onError(Throwable e) {
            safeShutdown();
            Log.e(TAG, "onError: ", e);
        }

        @Override
        public void onNext(GithubRepo githubRepo) {
            Log.e("RePo",githubRepo.name);
            rxBus.send(githubRepo);
        }
    }
}

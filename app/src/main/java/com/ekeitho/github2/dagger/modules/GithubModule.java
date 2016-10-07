package com.ekeitho.github2.dagger.modules;

import com.ekeitho.github2.dagger.ActivityScope;
import com.ekeitho.github2.model.GithubRepos;
import com.ekeitho.github2.model.GithubUser;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

@Module
public class GithubModule {

    public static final String BASE_URL = "https://api.github.com";


    public interface GithubEndpoint {

        @GET("users/{username}")
        Observable<GithubUser> getUser(@Path("username") String username);

        @GET("users/{username}/repos")
        Observable<List<GithubRepos>> getUserRepos(@Path("username") String username);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public GithubEndpoint provideGithubService(Retrofit retrofit) {
        return retrofit.create(GithubEndpoint.class);
    }

}

package com.about.country.dagger.module;

import android.content.Context;

import com.about.country.dagger.scope.AppScope;
import com.about.country.model.repo.AboutCountryService;
import com.about.country.model.repo.DataProvider;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class NetModule {
    private static final String APK_KEY = "HVlPvswuglileOHlRP9x8YNNz6Cqm1C3JWJDQ5dq";
    private String mBaseUrl;
    private Context context;

    public NetModule(String mBaseUrl, Context context) {
        this.mBaseUrl = mBaseUrl;
        this.context = context;
    }

    @Provides
    @AppScope
    OkHttpClient provideOkhttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        LoggingInterceptor loggingInterceptor=new LoggingInterceptor();
        client.addInterceptor(logging);
        return client.build();
    }

    @Provides
    @AppScope
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();

    }

    @Provides
    @AppScope
    AboutCountryService provideAboutCountryService(Retrofit retrofit) {
        return retrofit.create(AboutCountryService.class);
    }

    @Provides
    @AppScope
    Context provideAppContext() {
        return context;
    }

    @Provides
    @AppScope
    DataProvider provideDataprovider(Context context) {
        return new DataProvider(context);
    }


}

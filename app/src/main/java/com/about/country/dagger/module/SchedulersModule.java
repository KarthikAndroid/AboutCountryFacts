package com.about.country.dagger.module;

import com.about.country.dagger.annotation.IOScheduler;
import com.about.country.dagger.annotation.UiScheduler;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class SchedulersModule {

    @Provides
    @UiScheduler
    public Scheduler providesUiScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @IOScheduler
    public Scheduler providesIOScheduler() {
        return Schedulers.io();
    }
}

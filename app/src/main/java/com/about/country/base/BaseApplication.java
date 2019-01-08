package com.about.country.base;

import android.app.Application;

import com.about.country.dagger.AppComponent;

import com.about.country.dagger.DaggerAppComponent;
import com.about.country.dagger.module.AppModule;
import com.about.country.dagger.module.NetModule;
import com.about.country.model.repo.AboutCountryService;

public class BaseApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(AboutCountryService.BASE_URL, this))
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

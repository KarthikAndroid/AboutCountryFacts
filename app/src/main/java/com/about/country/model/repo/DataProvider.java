package com.about.country.model.repo;

import android.arch.lifecycle.MediatorLiveData;
import android.content.Context;

import com.about.country.base.BaseApplication;
import com.about.country.model.AboutCountryDetails;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Repository to load json and parse to a model
 */
public class DataProvider {

    private static DataProvider sInstance;
    private Context context;


    @Inject
    AboutCountryService aboutCountryService;


    private MediatorLiveData<AboutCountryDetails> mObservableAbountCountryItemList = new MediatorLiveData<>();

    private final static String JSON_FILE_COUNTRY_DETAIL = "countrydetails.json";

    public DataProvider(Context context) {
        this.context = context;
        ((BaseApplication) context).getAppComponent().inject(this);

    }

    public static DataProvider getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DataProvider.class) {
                if (sInstance == null) {
                    sInstance = new DataProvider(context);
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of items from the json file and get notified when the data changes.
     */
    public MediatorLiveData<AboutCountryDetails> getAboutCountryItemList() {

        return mObservableAbountCountryItemList;
    }


    public void requestIForAboutCountryDetails() {
        aboutCountryService.getAboutCountryDetail()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<AboutCountryDetails>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(AboutCountryDetails aboutCountryDetails) {
                        mObservableAbountCountryItemList.setValue(aboutCountryDetails);
                    }


                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


}

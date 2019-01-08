package com.about.country.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.about.country.base.BaseApplication;
import com.about.country.model.AboutCountryDetails;
import com.about.country.model.repo.DataProvider;

import javax.inject.Inject;


public class AboutContryViewModel extends AndroidViewModel {

    //@Inject repository here using Dagger
    @Inject
    DataProvider dataProvider = null;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<AboutCountryDetails> mObservableAboutCountryListItemList = new MediatorLiveData<>();

    public AboutContryViewModel(Application application) {
        super(application);
        ((BaseApplication) application).getAppComponent().inject(this);
//        dataProvider = DataProvider.getInstance(application);
        mObservableAboutCountryListItemList.addSource(dataProvider.getAboutCountryItemList(), mObservableAboutCountryListItemList::setValue);
        dataProvider.requestIForAboutCountryDetails();


    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<AboutCountryDetails> getAboutCountryItemsList() {
        return mObservableAboutCountryListItemList;
    }

    public void requestAboutCountryDetails() {
        dataProvider.requestIForAboutCountryDetails();
    }
}

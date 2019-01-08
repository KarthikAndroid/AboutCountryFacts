
package com.about.country.dagger;


import com.about.country.dagger.module.AppModule;
import com.about.country.dagger.module.NetModule;
import com.about.country.dagger.scope.AppScope;
import com.about.country.model.repo.DataProvider;
import com.about.country.viewmodel.AboutContryViewModel;

import dagger.Component;

@Component(modules = {AppModule.class, NetModule.class})
@AppScope
public interface AppComponent {

    void inject(AboutContryViewModel aboutContryViewModel);

    void inject(DataProvider dataProvider);


}

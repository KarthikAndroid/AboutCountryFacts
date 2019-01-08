
package com.about.country.dagger;


import com.about.country.dagger.module.AppModule;
import com.about.country.dagger.module.NetModule;
import com.about.country.dagger.module.SchedulersModule;
import com.about.country.dagger.scope.AppScope;

import dagger.Component;

@Component(modules = {AppModule.class, SchedulersModule.class, NetModule.class})
@AppScope
public interface AppComponent {


}

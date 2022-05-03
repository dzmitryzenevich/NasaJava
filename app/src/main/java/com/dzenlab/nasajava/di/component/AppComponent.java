package com.dzenlab.nasajava.di.component;

import com.dzenlab.nasajava.di.DatabaseModule;
import com.dzenlab.nasajava.di.NetworkModule;
import com.dzenlab.nasajava.di.module.AppModule;
import com.dzenlab.nasajava.di.module.DataModule;
import com.dzenlab.nasajava.di.SharePrefModule;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        DataModule.class,
        SharePrefModule.class,
        NetworkModule.class,
        DatabaseModule.class})
public interface AppComponent {

    FragmentComponent.Factory fragmentComponent();
}

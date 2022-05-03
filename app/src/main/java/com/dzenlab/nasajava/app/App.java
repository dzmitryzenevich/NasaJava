package com.dzenlab.nasajava.app;

import android.app.Application;
import com.dzenlab.nasajava.di.component.AppComponent;
import com.dzenlab.nasajava.di.component.DaggerAppComponent;
import com.dzenlab.nasajava.di.module.AppModule;

public class App extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {

        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}

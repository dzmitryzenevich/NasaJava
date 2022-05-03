package com.dzenlab.nasajava.di;

import static com.dzenlab.nasajava.data.sharepref.Constants.SHARED_PREFERENCES_NAME;
import android.content.Context;
import android.content.SharedPreferences;
import com.dzenlab.nasajava.data.sharepref.storage.SharedPrefStorage;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class SharePrefModule {

    @Provides
    @Singleton
    public SharedPrefStorage provideSharedPrefStorage(SharedPreferences sharedPreferences) {

        return new SharedPrefStorage(sharedPreferences);
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharePref(Context context) {

        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
}

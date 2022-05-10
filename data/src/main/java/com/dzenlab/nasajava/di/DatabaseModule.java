package com.dzenlab.nasajava.di;

import static com.dzenlab.nasajava.data.database.Constants.DATABASE_NAME;
import android.content.Context;
import androidx.room.Room;
import com.dzenlab.nasajava.data.database.AppDatabase;
import com.dzenlab.nasajava.data.database.storage.DatabaseStorage;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    public DatabaseStorage provideDatabaseStorage(AppDatabase appDatabase) {

        return new DatabaseStorage(appDatabase);
    }

    @Provides
    @Singleton
    public AppDatabase provideDatabase(Context context) {

        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
    }
}
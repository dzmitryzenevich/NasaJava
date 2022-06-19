package com.dzenlab.nasajava.di.module;

import com.dzenlab.nasajava.data.database.storage.DatabaseStorage;
import com.dzenlab.nasajava.data.network.storage.NetworkStorage;
import com.dzenlab.nasajava.data.repository.ItemRepositoryImpl;
import com.dzenlab.nasajava.data.sharepref.storage.PagingStorage;
import com.dzenlab.nasajava.repository.ItemRepository;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    public ItemRepository provideItemRepository(PagingStorage pagingStorage,
                                                NetworkStorage networkStorage,
                                                DatabaseStorage databaseStorage) {

        return new ItemRepositoryImpl(
                pagingStorage,
                networkStorage,
                databaseStorage);
    }
}

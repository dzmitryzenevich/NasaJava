package com.dzenlab.nasajava.di.module;

import com.dzenlab.nasajava.presentation.fragment.MainViewModelFactory;
import com.dzenlab.nasajava.usecase.database.DeleteItemFromDatabaseUseCase;
import com.dzenlab.nasajava.usecase.database.GetItemFromDatabaseUseCase;
import com.dzenlab.nasajava.usecase.network.GetItemListFromNetworkUseCase;
import com.dzenlab.nasajava.usecase.sharepref.GetLastNumberUseCase;
import com.dzenlab.nasajava.usecase.database.SaveItemInDatabaseUseCase;
import com.dzenlab.nasajava.usecase.sharepref.SetLastNumberUseCase;
import com.dzenlab.nasajava.usecase.sharepref.StateUrlPictureUseCase;
import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    @Provides
    public MainViewModelFactory provideMVMFactory(GetLastNumberUseCase getLastNumber,
                                                  SetLastNumberUseCase setLastNumber,
                                                  StateUrlPictureUseCase stateUrlPictureUseCase,
                                                  GetItemListFromNetworkUseCase getItemListFromNet,
                                                  GetItemFromDatabaseUseCase getItemDB,
                                                  SaveItemInDatabaseUseCase saveItemDB,
                                                  DeleteItemFromDatabaseUseCase deleteItemDB) {

        return new MainViewModelFactory(
                getLastNumber,
                setLastNumber,
                stateUrlPictureUseCase,
                getItemListFromNet,
                getItemDB,
                saveItemDB,
                deleteItemDB);
    }
}
package com.dzenlab.nasajava.di.module;

import com.dzenlab.nasajava.presentation.fragment.MainViewModelFactory;
import com.dzenlab.nasajava.usecase.DeleteItemUseCase;
import com.dzenlab.nasajava.usecase.GetItemIdUseCase;
import com.dzenlab.nasajava.usecase.LoadItemsUseCase;
import com.dzenlab.nasajava.usecase.SavePositionAndIdUseCase;
import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    @Provides
    public MainViewModelFactory provideMVMFactory(SavePositionAndIdUseCase SavePositionAndIdUseCase,
                                                  LoadItemsUseCase loadItemsUseCase,
                                                  DeleteItemUseCase deleteItemUseCase,
                                                  GetItemIdUseCase getItemIdUseCase) {

        return new MainViewModelFactory(
                SavePositionAndIdUseCase,
                getItemIdUseCase,
                loadItemsUseCase,
                deleteItemUseCase);
    }
}
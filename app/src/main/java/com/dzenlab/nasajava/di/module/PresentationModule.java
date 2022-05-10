package com.dzenlab.nasajava.di.module;

import com.dzenlab.nasajava.presentation.fragment.MainViewModelFactory;
import com.dzenlab.nasajava.usecase.DeleteItemUseCase;
import com.dzenlab.nasajava.usecase.GetItemIdUseCase;
import com.dzenlab.nasajava.usecase.LoadItemsUseCase;
import com.dzenlab.nasajava.usecase.SavePositionAndIdUseCase;
import com.dzenlab.nasajava.usecase.StateUrlPictureUseCase;
import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    @Provides
    public MainViewModelFactory provideMVMFactory(SavePositionAndIdUseCase SavePositionAndIdUseCase,
                                                  StateUrlPictureUseCase stateUrlPictureUseCase,
                                                  LoadItemsUseCase loadItemsUseCase,
                                                  DeleteItemUseCase deleteItemUseCase,
                                                  GetItemIdUseCase getItemIdUseCase) {

        return new MainViewModelFactory(
                SavePositionAndIdUseCase,
                getItemIdUseCase,
                stateUrlPictureUseCase,
                loadItemsUseCase,
                deleteItemUseCase);
    }
}
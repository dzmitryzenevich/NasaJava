package com.dzenlab.nasajava.presentation.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.dzenlab.nasajava.usecase.DeleteItemUseCase;
import com.dzenlab.nasajava.usecase.GetItemIdUseCase;
import com.dzenlab.nasajava.usecase.LoadItemsUseCase;
import com.dzenlab.nasajava.usecase.SavePositionAndIdUseCase;
import com.dzenlab.nasajava.usecase.StateUrlPictureUseCase;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final SavePositionAndIdUseCase savePositionAndIdUseCase;

    private final GetItemIdUseCase getItemIdUseCase;

    private final StateUrlPictureUseCase stateUrlPictureUseCase;

    private final LoadItemsUseCase loadItemsUseCase;

    private final DeleteItemUseCase deleteItemUseCase;


    public MainViewModelFactory(SavePositionAndIdUseCase savePositionAndIdUseCase,
                                GetItemIdUseCase getItemIdUseCase,
                                StateUrlPictureUseCase stateUrlPictureUseCase,
                                LoadItemsUseCase loadItemsUseCase,
                                DeleteItemUseCase deleteItemUseCase) {

        this.savePositionAndIdUseCase = savePositionAndIdUseCase;

        this.getItemIdUseCase = getItemIdUseCase;

        this.stateUrlPictureUseCase = stateUrlPictureUseCase;

        this.loadItemsUseCase = loadItemsUseCase;

        this.deleteItemUseCase = deleteItemUseCase;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {

        return (T) new MainViewModel(
                savePositionAndIdUseCase,
                getItemIdUseCase,
                stateUrlPictureUseCase,
                loadItemsUseCase,
                deleteItemUseCase);
    }
}

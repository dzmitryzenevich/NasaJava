package com.dzenlab.nasajava.presentation.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.dzenlab.nasajava.usecase.database.DeleteItemFromDatabaseUseCase;
import com.dzenlab.nasajava.usecase.database.GetItemFromDatabaseUseCase;
import com.dzenlab.nasajava.usecase.network.GetItemListFromNetworkUseCase;
import com.dzenlab.nasajava.usecase.sharepref.GetLastNumberUseCase;
import com.dzenlab.nasajava.usecase.database.SaveItemInDatabaseUseCase;
import com.dzenlab.nasajava.usecase.sharepref.SetLastNumberUseCase;
import com.dzenlab.nasajava.usecase.sharepref.StateUrlPictureUseCase;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final GetLastNumberUseCase getLastNumberUseCase;

    private final SetLastNumberUseCase setLastNumberUseCase;

    private final StateUrlPictureUseCase stateUrlPictureUseCase;

    private final GetItemListFromNetworkUseCase getItemListFromNetworkUseCase;

    private final GetItemFromDatabaseUseCase getItemFromDatabaseUseCase;

    private final SaveItemInDatabaseUseCase saveItemInDatabaseUseCase;

    private final DeleteItemFromDatabaseUseCase deleteItemFromDatabaseUseCase;


    public MainViewModelFactory(GetLastNumberUseCase getLastNumberUseCase,
                                SetLastNumberUseCase setLastNumberUseCase,
                                StateUrlPictureUseCase stateUrlPictureUseCase,
                                GetItemListFromNetworkUseCase getItemListFromNetworkUseCase,
                                GetItemFromDatabaseUseCase getItemFromDatabaseUseCase,
                                SaveItemInDatabaseUseCase saveItemInDatabaseUseCase,
                                DeleteItemFromDatabaseUseCase deleteItemFromDatabaseUseCase) {

        this.getLastNumberUseCase = getLastNumberUseCase;

        this.setLastNumberUseCase = setLastNumberUseCase;

        this.stateUrlPictureUseCase = stateUrlPictureUseCase;

        this.getItemListFromNetworkUseCase = getItemListFromNetworkUseCase;

        this.getItemFromDatabaseUseCase = getItemFromDatabaseUseCase;

        this.saveItemInDatabaseUseCase = saveItemInDatabaseUseCase;

        this.deleteItemFromDatabaseUseCase = deleteItemFromDatabaseUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {

        //noinspection unchecked
        return (T) new MainViewModel(
                getLastNumberUseCase,
                setLastNumberUseCase,
                stateUrlPictureUseCase,
                getItemListFromNetworkUseCase,
                getItemFromDatabaseUseCase,
                saveItemInDatabaseUseCase,
                deleteItemFromDatabaseUseCase);
    }
}

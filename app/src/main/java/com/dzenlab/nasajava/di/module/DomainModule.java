package com.dzenlab.nasajava.di.module;

import com.dzenlab.nasajava.repository.ItemRepository;
import com.dzenlab.nasajava.usecase.DeleteItemUseCase;
import com.dzenlab.nasajava.usecase.GetItemIdUseCase;
import com.dzenlab.nasajava.usecase.LoadItemsUseCase;
import com.dzenlab.nasajava.usecase.SavePositionAndIdUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class DomainModule {

    @Provides
    public GetItemIdUseCase provideGetItemIdUseCase(ItemRepository itemRepository) {

        return new GetItemIdUseCase(itemRepository);
    }

    @Provides
    public SavePositionAndIdUseCase provideSavePositionAndIdUseCase(ItemRepository itemRepository) {

        return new SavePositionAndIdUseCase(itemRepository);
    }

    @Provides
    public LoadItemsUseCase provideLoadItemsUseCase(ItemRepository itemRepository) {

        return new LoadItemsUseCase(itemRepository);
    }

    @Provides
    public DeleteItemUseCase provideDeleteItemUseCase(ItemRepository itemRepository) {

        return new DeleteItemUseCase(itemRepository);
    }
}

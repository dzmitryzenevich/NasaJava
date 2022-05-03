package com.dzenlab.nasajava.di.module;

import com.dzenlab.nasajava.repository.ItemRepository;
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
public class DomainModule {

    @Provides
    public GetLastNumberUseCase provideGetLastNumberUseCase(ItemRepository itemRepository) {

        return new GetLastNumberUseCase(itemRepository);
    }

    @Provides
    public SetLastNumberUseCase provideSetLastNumberUseCase(ItemRepository itemRepository) {

        return new SetLastNumberUseCase(itemRepository);
    }

    @Provides
    public StateUrlPictureUseCase provideStateUrlPictureUseCase(ItemRepository itemRepository) {

        return new StateUrlPictureUseCase(itemRepository);
    }

    @Provides
    public GetItemListFromNetworkUseCase provideGetItemListFromNetworkUseCase(ItemRepository
                                                                              itemRepository) {

        return new GetItemListFromNetworkUseCase(itemRepository);
    }

    @Provides
    public GetItemFromDatabaseUseCase provideGetItemFromDatabaseUseCase(ItemRepository
                                                                        itemRepository) {

        return new GetItemFromDatabaseUseCase(itemRepository);
    }

    @Provides
    public SaveItemInDatabaseUseCase provideSaveItemInDatabaseUseCase(ItemRepository
                                                                      itemRepository) {

        return new SaveItemInDatabaseUseCase(itemRepository);
    }

    @Provides
    public DeleteItemFromDatabaseUseCase provideDeleteItemFromDatabaseUseCase(ItemRepository
                                                                              itemRepository) {

        return new DeleteItemFromDatabaseUseCase(itemRepository);
    }
}

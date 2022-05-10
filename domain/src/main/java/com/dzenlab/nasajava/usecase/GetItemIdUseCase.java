package com.dzenlab.nasajava.usecase;

import com.dzenlab.nasajava.models.PagingDataSP;
import com.dzenlab.nasajava.repository.ItemRepository;
import io.reactivex.Single;

public class GetItemIdUseCase {

    private final ItemRepository itemRepository;


    public GetItemIdUseCase(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    public Single<PagingDataSP> execute() {

        return itemRepository.getItemId();
    }
}

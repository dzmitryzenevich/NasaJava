package com.dzenlab.nasajava.usecase;

import com.dzenlab.nasajava.models.PagingDataSP;
import com.dzenlab.nasajava.repository.ItemRepository;
import io.reactivex.Completable;

public class SavePositionAndIdUseCase {

    private final ItemRepository itemRepository;


    public SavePositionAndIdUseCase(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    public Completable execute(int position, int id) {

        return itemRepository.setPosition(new PagingDataSP(position))
                .andThen(itemRepository.setItemId(new PagingDataSP(id)));
    }
}

package com.dzenlab.nasajava.usecase;

import com.dzenlab.nasajava.models.ItemNet;
import com.dzenlab.nasajava.repository.ItemRepository;
import io.reactivex.Completable;

public class DeleteItemUseCase {

    private final ItemRepository itemRepository;


    public DeleteItemUseCase(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    public Completable execute(ItemNet itemNet) {

        return itemRepository.deleteItem(itemNet);
    }
}

package com.dzenlab.nasajava.usecase.network;

import com.dzenlab.nasajava.models.ItemNet;
import com.dzenlab.nasajava.repository.ItemRepository;
import java.util.List;
import io.reactivex.Single;

//import java.util.logging.Level;
//import java.util.logging.Logger;
//Logger.getLogger("MY_LOG").log(Level.INFO, "");

public class GetItemListFromNetworkUseCase {

    private final ItemRepository itemRepository;


    public GetItemListFromNetworkUseCase(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    public Single<List<ItemNet>> execute() {

        return itemRepository.getItemList();
    }
}

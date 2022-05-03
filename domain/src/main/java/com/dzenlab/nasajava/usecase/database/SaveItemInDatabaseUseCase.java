package com.dzenlab.nasajava.usecase.database;

import com.dzenlab.nasajava.models.ItemNet;
import com.dzenlab.nasajava.repository.ItemRepository;
import java.util.List;
import io.reactivex.Completable;

//import java.util.logging.Level;
//import java.util.logging.Logger;
//Logger.getLogger("MY_LOG").log(Level.INFO, "");

public class SaveItemInDatabaseUseCase {

    private final ItemRepository itemRepository;


    public SaveItemInDatabaseUseCase(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    public Completable execute(List<ItemNet> list) {

        return itemRepository.insertAll(list);
    }
}

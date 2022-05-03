package com.dzenlab.nasajava.usecase.database;

import com.dzenlab.nasajava.models.ItemDeleteDB;
import com.dzenlab.nasajava.repository.ItemRepository;
import io.reactivex.Completable;

//import java.util.logging.Level;
//import java.util.logging.Logger;
//Logger.getLogger("MY_LOG").log(Level.INFO, "");

public class DeleteItemFromDatabaseUseCase {

    private final ItemRepository itemRepository;


    public DeleteItemFromDatabaseUseCase(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    public Completable execute(ItemDeleteDB itemDeleteDB) {

        return itemRepository.deleteById(itemDeleteDB);
    }
}

package com.dzenlab.nasajava.usecase.database;

import com.dzenlab.nasajava.models.ItemNet;
import com.dzenlab.nasajava.models.ResponseDB;
import com.dzenlab.nasajava.repository.ItemRepository;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Flowable;

//import java.util.logging.Level;
//import java.util.logging.Logger;
//Logger.getLogger("MY_LOG").log(Level.INFO, "");

public class GetItemFromDatabaseUseCase {

    private final ItemRepository itemRepository;


    public GetItemFromDatabaseUseCase(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    public Flowable<ResponseDB> execute() {

        return itemRepository.getAll()
                .map(this::getResponse)
                .onErrorReturn(this::loadFromDBError);
    }

    private ResponseDB getResponse(List<ItemNet> list) {

        return new ResponseDB(list, null);
    }

    private ResponseDB loadFromDBError(Throwable t) {

        String error;

        if(t.getMessage() != null) {

            error = t.getMessage();

        } else {

            error = "";
        }

        return new ResponseDB(new ArrayList<>(), error);
    }
}

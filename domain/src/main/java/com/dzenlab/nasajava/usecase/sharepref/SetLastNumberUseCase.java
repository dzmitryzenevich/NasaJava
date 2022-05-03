package com.dzenlab.nasajava.usecase.sharepref;

import com.dzenlab.nasajava.models.NumberSP;
import com.dzenlab.nasajava.repository.ItemRepository;
import io.reactivex.Completable;

//import java.util.logging.Level;
//import java.util.logging.Logger;
//Logger.getLogger("MY_LOG").log(Level.INFO, "");

public class SetLastNumberUseCase {

    private final ItemRepository itemRepository;


    public SetLastNumberUseCase(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    public Completable execute(NumberSP numberSP) {

        return itemRepository.setLastNumber(numberSP);
    }
}

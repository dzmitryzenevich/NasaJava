package com.dzenlab.nasajava.usecase.sharepref;

import com.dzenlab.nasajava.models.NumberSP;
import com.dzenlab.nasajava.repository.ItemRepository;

//import java.util.logging.Level;
//import java.util.logging.Logger;
//Logger.getLogger("MY_LOG").log(Level.INFO, "");

public class GetLastNumberUseCase {

    private final ItemRepository itemRepository;


    public GetLastNumberUseCase(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    public NumberSP execute() {

        return itemRepository.getLastNumber();
    }
}

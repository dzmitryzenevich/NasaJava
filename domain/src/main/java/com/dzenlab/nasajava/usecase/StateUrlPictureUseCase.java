package com.dzenlab.nasajava.usecase;

import com.dzenlab.nasajava.models.StatePictureSP;
import com.dzenlab.nasajava.models.StateUrlPictureSP;
import com.dzenlab.nasajava.models.UrlPictureSP;
import com.dzenlab.nasajava.repository.ItemRepository;
import org.jetbrains.annotations.Nullable;
import io.reactivex.schedulers.Schedulers;

public class StateUrlPictureUseCase {

    private final ItemRepository itemRepository;


    public StateUrlPictureUseCase(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    public StateUrlPictureSP execute(@Nullable Boolean isOpenPicture,
                                     @Nullable String urlPicture) {

        StateUrlPictureSP stateUrlPictureSP = itemRepository.getSateUrlPicture();

        boolean isOpen = stateUrlPictureSP.isOpen();

        String url = stateUrlPictureSP.getUrl();

        if(isOpenPicture != null && isOpenPicture != isOpen) {

            itemRepository.setStatePicture(new StatePictureSP(isOpenPicture))
                    .subscribeOn(Schedulers.io())
                    .subscribe();
        }

        if(urlPicture != null && !urlPicture.equals(url)) {

            itemRepository.setUrl(new UrlPictureSP(urlPicture))
                    .subscribeOn(Schedulers.io())
                    .subscribe();
        }

        return new StateUrlPictureSP(isOpen, url);
    }
}

package com.dzenlab.nasajava.repository;

import com.dzenlab.nasajava.models.ItemNet;
import com.dzenlab.nasajava.models.ItemDeleteDB;
import com.dzenlab.nasajava.models.NumberSP;
import com.dzenlab.nasajava.models.StatePictureSP;
import com.dzenlab.nasajava.models.StateUrlPictureSP;
import com.dzenlab.nasajava.models.UrlPictureSP;

import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface ItemRepository {

    NumberSP getLastNumber();

    Completable setLastNumber(NumberSP numberSP);

    StateUrlPictureSP getSateUrlPicture();

    Completable setUrl(UrlPictureSP urlPictureSP);

    Completable setStatePicture(StatePictureSP statePictureSP);

    Single<List<ItemNet>> getItemList();

    Flowable<List<ItemNet>> getAll();

    Completable insertAll(List<ItemNet> list);

    Completable deleteById(ItemDeleteDB itemDeleteDB);
}

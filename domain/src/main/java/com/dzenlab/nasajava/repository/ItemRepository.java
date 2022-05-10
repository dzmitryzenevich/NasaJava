package com.dzenlab.nasajava.repository;

import com.dzenlab.nasajava.models.ItemNet;
import com.dzenlab.nasajava.models.PagingDataSP;
import com.dzenlab.nasajava.models.QueryDB;
import com.dzenlab.nasajava.models.StatePictureSP;
import com.dzenlab.nasajava.models.StateUrlPictureSP;
import com.dzenlab.nasajava.models.UrlPictureSP;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Single;

public interface ItemRepository {

    Single<PagingDataSP> getPosition();

    Completable setPosition(PagingDataSP pagingDataSP);

    Single<PagingDataSP> getMinPage();

    Completable setMinPage(PagingDataSP pagingDataSP);

    Single<PagingDataSP> getItemId();

    Completable setItemId(PagingDataSP pagingDataSP);

    StateUrlPictureSP getSateUrlPicture();

    Completable setUrl(UrlPictureSP urlPictureSP);

    Completable setStatePicture(StatePictureSP statePictureSP);

    Single<List<ItemNet>> getItemList();

    Single<Integer> getCount();

    List<ItemNet> getAll(QueryDB queryDB);

    Completable insertAll(List<ItemNet> list);

    Completable deleteItem(ItemNet itemNet);
}

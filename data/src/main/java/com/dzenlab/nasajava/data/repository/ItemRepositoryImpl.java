package com.dzenlab.nasajava.data.repository;

import com.dzenlab.nasajava.data.database.models.Item;
import com.dzenlab.nasajava.data.database.models.Query;
import com.dzenlab.nasajava.data.database.storage.DatabaseStorage;
import com.dzenlab.nasajava.data.mapper.Mapper;
import com.dzenlab.nasajava.data.network.storage.NetworkStorage;
import com.dzenlab.nasajava.data.sharepref.models.PagingData;
import com.dzenlab.nasajava.data.sharepref.models.StatePicture;
import com.dzenlab.nasajava.data.sharepref.models.UrlPicture;
import com.dzenlab.nasajava.data.sharepref.storage.PagingStorage;
import com.dzenlab.nasajava.data.sharepref.storage.PictureStorage;
import com.dzenlab.nasajava.models.ItemNet;
import com.dzenlab.nasajava.models.PagingDataSP;
import com.dzenlab.nasajava.models.QueryDB;
import com.dzenlab.nasajava.models.StatePictureSP;
import com.dzenlab.nasajava.models.StateUrlPictureSP;
import com.dzenlab.nasajava.models.UrlPictureSP;
import com.dzenlab.nasajava.repository.ItemRepository;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Single;

public class ItemRepositoryImpl implements ItemRepository {

    private final PagingStorage pagingStorage;

    private final PictureStorage pictureStorage;

    private final NetworkStorage networkStorage;

    private final DatabaseStorage databaseStorage;


    public ItemRepositoryImpl(PagingStorage pagingStorage,
                              PictureStorage pictureStorage,
                              NetworkStorage networkStorage,
                              DatabaseStorage databaseStorage) {

        this.pagingStorage = pagingStorage;

        this.pictureStorage = pictureStorage;

        this.networkStorage = networkStorage;

        this.databaseStorage = databaseStorage;
    }

    @Override
    public Single<PagingDataSP> getPosition() {

        return pagingStorage.getPosition().map(data -> new PagingDataSP(data.getData()));
    }

    @Override
    public Completable setPosition(PagingDataSP pagingDataSP) {

        return pagingStorage.setPosition(new PagingData(pagingDataSP.getData()));
    }

    @Override
    public Single<PagingDataSP> getMinPage() {

        return pagingStorage.getMinPage().map(data -> new PagingDataSP(data.getData()));
    }

    @Override
    public Completable setMinPage(PagingDataSP pagingDataSP) {

        return pagingStorage.setMinPage(new PagingData(pagingDataSP.getData()));
    }

    @Override
    public Single<PagingDataSP> getItemId() {

        return pagingStorage.getItemId().map(data -> new PagingDataSP(data.getData()));
    }

    @Override
    public Completable setItemId(PagingDataSP pagingDataSP) {

        return pagingStorage.setItemId(new PagingData(pagingDataSP.getData()));
    }

    @Override
    public StateUrlPictureSP getSateUrlPicture() {

        return Mapper.getStateUrlPictureSP(pictureStorage.getSateUrlPicture());
    }

    @Override
    public Completable setUrl(UrlPictureSP urlPictureSP) {

        return pictureStorage.setUrl(new UrlPicture(urlPictureSP.getUrl()));
    }

    @Override
    public Completable setStatePicture(StatePictureSP statePictureSP) {

        return pictureStorage.setStatePictureSP(new StatePicture(statePictureSP.isOpen()));
    }

    @Override
    public Single<List<ItemNet>> getItemList() {

        return networkStorage.getItemList().map(Mapper::getItemListFromNetList);
    }

    @Override
    public Single<Integer> getCount() {

        return databaseStorage.getCount();
    }

    @Override
    public List<ItemNet> getAll(QueryDB queryDB) {

        return Mapper.getItemListFromDBList(databaseStorage.getAll(
                new Query(queryDB.getLimit(), queryDB.getOffset())));
    }

    @Override
    public Completable insertAll(List<ItemNet> list) {

        return databaseStorage.insertAll(Mapper.getItemDBListFromItemList(list));
    }

    @Override
    public Completable deleteItem(ItemNet itemNet) {

        return databaseStorage.deleteItem(new Item(
                itemNet.getId(), itemNet.getDate(), itemNet.getTitle(), itemNet.getUrl()));
    }
}
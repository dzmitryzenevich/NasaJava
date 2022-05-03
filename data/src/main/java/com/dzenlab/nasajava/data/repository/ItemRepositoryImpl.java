package com.dzenlab.nasajava.data.repository;

import com.dzenlab.nasajava.data.database.models.ItemDelete;
import com.dzenlab.nasajava.data.database.storage.DatabaseStorage;
import com.dzenlab.nasajava.data.mapper.Mapper;
import com.dzenlab.nasajava.data.network.storage.NetworkStorage;
import com.dzenlab.nasajava.data.sharepref.models.Number;
import com.dzenlab.nasajava.data.sharepref.models.StatePicture;
import com.dzenlab.nasajava.data.sharepref.models.UrlPicture;
import com.dzenlab.nasajava.data.sharepref.storage.SharedPrefStorage;
import com.dzenlab.nasajava.models.ItemNet;
import com.dzenlab.nasajava.models.ItemDeleteDB;
import com.dzenlab.nasajava.models.NumberSP;
import com.dzenlab.nasajava.models.StatePictureSP;
import com.dzenlab.nasajava.models.StateUrlPictureSP;
import com.dzenlab.nasajava.models.UrlPictureSP;
import com.dzenlab.nasajava.repository.ItemRepository;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class ItemRepositoryImpl implements ItemRepository {

    private final SharedPrefStorage sharedPrefStorage;

    private final NetworkStorage networkStorage;

    private final DatabaseStorage databaseStorage;


    public ItemRepositoryImpl(SharedPrefStorage sharedPrefStorage,
                              NetworkStorage networkStorage,
                              DatabaseStorage databaseStorage) {

        this.sharedPrefStorage = sharedPrefStorage;

        this.networkStorage = networkStorage;

        this.databaseStorage = databaseStorage;
    }

    @Override
    public NumberSP getLastNumber() {

        return new NumberSP(sharedPrefStorage.getNumber().getNumber());
    }

    @Override
    public Completable setLastNumber(NumberSP numberSP) {

        return sharedPrefStorage.setNumber(new Number(numberSP.getNumber()));
    }

    @Override
    public StateUrlPictureSP getSateUrlPicture() {

        return Mapper.getStateUrlPictureSP(sharedPrefStorage.getSateUrlPicture());
    }

    @Override
    public Completable setUrl(UrlPictureSP urlPictureSP) {

        return sharedPrefStorage.setUrl(new UrlPicture(urlPictureSP.getUrl()));
    }

    @Override
    public Completable setStatePicture(StatePictureSP statePictureSP) {

        return sharedPrefStorage.setStatePictureSP(new StatePicture(statePictureSP.isOpen()));
    }

    @Override
    public Single<List<ItemNet>> getItemList() {

        return networkStorage.getItemList().map(Mapper::getItemListFromNetList);
    }

    @Override
    public Flowable<List<ItemNet>> getAll() {

        return databaseStorage.getAll().map(Mapper::getItemListFromDBList);
    }

    @Override
    public Completable insertAll(List<ItemNet> list) {

        return databaseStorage.insertAll(Mapper.getItemDBListFromItemList(list));
    }

    @Override
    public Completable deleteById(ItemDeleteDB itemDeleteDB) {

        return databaseStorage.deleteById(new ItemDelete(itemDeleteDB.getId()));
    }
}
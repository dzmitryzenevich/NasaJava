package com.dzenlab.nasajava.data.database.storage;

import com.dzenlab.nasajava.data.database.AppDatabase;
import com.dzenlab.nasajava.data.database.models.Item;
import com.dzenlab.nasajava.data.database.models.ItemDelete;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class DatabaseStorage {

    private final AppDatabase appDatabase;

    public DatabaseStorage(AppDatabase appDatabase) {

        this.appDatabase = appDatabase;
    }

    public Flowable<List<Item>> getAll() {

        return appDatabase.getItemDao().getAll();
    }

    public Completable insertAll(List<Item> list) {

        return appDatabase.getItemDao().insertAll(list);
    }

    public Completable deleteById(ItemDelete itemDelete) {

        return appDatabase.getItemDao().deleteById(itemDelete);
    }
}

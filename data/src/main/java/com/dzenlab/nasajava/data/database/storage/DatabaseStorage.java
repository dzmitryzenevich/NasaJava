package com.dzenlab.nasajava.data.database.storage;

import com.dzenlab.nasajava.data.database.AppDatabase;
import com.dzenlab.nasajava.data.database.models.Item;
import com.dzenlab.nasajava.data.database.models.Query;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Single;

public class DatabaseStorage {

    private final AppDatabase appDatabase;

    public DatabaseStorage(AppDatabase appDatabase) {

        this.appDatabase = appDatabase;
    }

    public Single<Integer> getCount() {

        return appDatabase.getItemDao().getCount();
    }

    public List<Item> getAll(Query query) {

        return appDatabase.getItemDao().getAll(query.getLimit(), query.getOffset());
    }

    public Completable insertAll(List<Item> list) {

        return appDatabase.getItemDao().insertAll(list);
    }

    public Completable deleteItem(Item item) {

        return appDatabase.getItemDao().deleteItem(item);
    }
}

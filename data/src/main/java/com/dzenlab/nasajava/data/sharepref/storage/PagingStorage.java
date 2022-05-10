package com.dzenlab.nasajava.data.sharepref.storage;

import static com.dzenlab.nasajava.data.sharepref.Constants.ITEM_ID_KEY;
import static com.dzenlab.nasajava.data.sharepref.Constants.MIN_PAGE_KEY;
import static com.dzenlab.nasajava.data.sharepref.Constants.POSITION_KEY;
import android.content.SharedPreferences;
import com.dzenlab.nasajava.data.sharepref.models.PagingData;
import io.reactivex.Completable;
import io.reactivex.Single;

public class PagingStorage {

    private final SharedPreferences sharedPreferences;


    public PagingStorage(SharedPreferences sharedPreferences) {

        this.sharedPreferences = sharedPreferences;
    }

    public Single<PagingData> getPosition() {

        int position = sharedPreferences.getInt(POSITION_KEY, 0);

        return Single.just(new PagingData(position));
    }

    public Completable setPosition(PagingData pagingData) {

        return Completable.create(emitter -> {

            sharedPreferences.edit().putInt(POSITION_KEY, pagingData.getData()).apply();

            emitter.onComplete();
        });
    }

    public Single<PagingData> getMinPage() {

        int position = sharedPreferences.getInt(MIN_PAGE_KEY, 0);

        return Single.just(new PagingData(position));
    }

    public Completable setMinPage(PagingData pagingData) {

        return Completable.create(emitter -> {

            sharedPreferences.edit().putInt(MIN_PAGE_KEY, pagingData.getData()).apply();

            emitter.onComplete();
        });
    }

    public Single<PagingData> getItemId() {

        int position = sharedPreferences.getInt(ITEM_ID_KEY, -1);

        return Single.just(new PagingData(position));
    }

    public Completable setItemId(PagingData pagingData) {

        return Completable.create(emitter -> {

            sharedPreferences.edit().putInt(ITEM_ID_KEY, pagingData.getData()).apply();

            emitter.onComplete();
        });
    }
}
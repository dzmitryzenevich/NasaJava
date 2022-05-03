package com.dzenlab.nasajava.data.sharepref.storage;

import static com.dzenlab.nasajava.data.sharepref.Constants.NUMBER_KEY;
import static com.dzenlab.nasajava.data.sharepref.Constants.STATE_PICTURE_KEY;
import static com.dzenlab.nasajava.data.sharepref.Constants.URL_PICTURE_KEY;

import android.content.SharedPreferences;
import com.dzenlab.nasajava.data.sharepref.models.Number;
import com.dzenlab.nasajava.data.sharepref.models.StatePicture;
import com.dzenlab.nasajava.data.sharepref.models.StateUrlPicture;
import com.dzenlab.nasajava.data.sharepref.models.UrlPicture;

import io.reactivex.Completable;

public class SharedPrefStorage {

    private final SharedPreferences sharedPreferences;


    public SharedPrefStorage(SharedPreferences sharedPreferences) {

        this.sharedPreferences = sharedPreferences;
    }

    public Number getNumber() {

        return new Number(sharedPreferences.getInt(NUMBER_KEY, 0));
    }

    public Completable setNumber(Number number) {

        return Completable.create(emitter -> {

            sharedPreferences.edit().putInt(NUMBER_KEY, number.getNumber()).apply();

            emitter.onComplete();
        });
    }

    public StateUrlPicture getSateUrlPicture() {

        boolean isOpen = sharedPreferences.getBoolean(STATE_PICTURE_KEY, false);

        String url = sharedPreferences.getString(URL_PICTURE_KEY, "");

        return new StateUrlPicture(isOpen, url);
    }

    public Completable setUrl(UrlPicture urlPicture) {

        return Completable.create(emitter -> {

            sharedPreferences.edit().putString(URL_PICTURE_KEY, urlPicture.getUrl()).apply();

            emitter.onComplete();
        });
    }

    public Completable setStatePictureSP(StatePicture statePicture) {

        return Completable.create(emitter -> {

            sharedPreferences.edit().putBoolean(STATE_PICTURE_KEY, statePicture.isOpen()).apply();

            emitter.onComplete();
        });
    }
}

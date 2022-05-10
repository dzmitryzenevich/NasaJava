package com.dzenlab.nasajava.data.sharepref.storage;

import static com.dzenlab.nasajava.data.sharepref.Constants.STATE_PICTURE_KEY;
import static com.dzenlab.nasajava.data.sharepref.Constants.URL_PICTURE_KEY;
import android.content.SharedPreferences;
import com.dzenlab.nasajava.data.sharepref.models.StatePicture;
import com.dzenlab.nasajava.data.sharepref.models.StateUrlPicture;
import com.dzenlab.nasajava.data.sharepref.models.UrlPicture;
import io.reactivex.Completable;

public class PictureStorage {

    private final SharedPreferences sharedPreferences;


    public PictureStorage(SharedPreferences sharedPreferences) {

        this.sharedPreferences = sharedPreferences;
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

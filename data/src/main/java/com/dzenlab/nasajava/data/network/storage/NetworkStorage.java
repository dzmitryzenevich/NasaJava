package com.dzenlab.nasajava.data.network.storage;

import static com.dzenlab.nasajava.data.network.Constants.QUERY_API_KEY_VALUE;
import static com.dzenlab.nasajava.data.network.Constants.QUERY_COUNT_VALUE;
import com.dzenlab.nasajava.data.network.api.NasaApi;
import com.dzenlab.nasajava.data.network.models.Item;
import java.util.List;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkStorage {

    private final Retrofit retrofit;


    public NetworkStorage(Retrofit retrofit) {

        this.retrofit = retrofit;
    }

    public Single<Response<List<Item>>> getItemList() {

        return retrofit.create(NasaApi.class).getItemList(QUERY_API_KEY_VALUE, QUERY_COUNT_VALUE);
    }
}

package com.dzenlab.nasajava.data.network.api;

import static com.dzenlab.nasajava.data.network.Constants.PLANETARY_URL;
import static com.dzenlab.nasajava.data.network.Constants.QUERY_API_KEY_KEY;
import static com.dzenlab.nasajava.data.network.Constants.QUERY_COUNT_KEY;
import com.dzenlab.nasajava.data.network.models.Item;
import java.util.List;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NasaApi {

    @GET(PLANETARY_URL)
    Single<Response<List<Item>>> getItemList(@Query(QUERY_API_KEY_KEY) String apiKey,
                                             @Query(QUERY_COUNT_KEY) int count);
}

package com.dzenlab.nasajava.di;

import static com.dzenlab.nasajava.data.network.Constants.*;
import android.content.Context;
import com.dzenlab.nasajava.data.network.storage.NetworkStorage;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Headers;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public NetworkStorage provideNetworkStorage(Retrofit retrofit) {

        return new NetworkStorage(retrofit);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory,
                                    OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory
                        .createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    @Singleton
    public GsonConverterFactory provideGsonConverterFactory() {

        return GsonConverterFactory.create(new GsonBuilder().setDateFormat(DATE_FORMAT).create());
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Context context) {

        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_SECOND, TimeUnit.SECONDS)
                .readTimeout(READ_WRITE_TIMEOUT_SECOND, TimeUnit.SECONDS)
                .writeTimeout(READ_WRITE_TIMEOUT_SECOND, TimeUnit.SECONDS)
                .cache(new Cache(context.getCacheDir(), CACHE_SIZE))
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Headers.Builder builder = request.headers().newBuilder();
                    builder.add(CACHE_CONTROL_HEADER, CACHE_CONTROL_VALUE);
                    builder.add(CONTENT_TYPE_HEADER, CONTENT_TYPE_APP_JSON);
                    request = request.newBuilder().headers(builder.build()).build();
                    return chain.proceed(request);
                }).build();
    }
}
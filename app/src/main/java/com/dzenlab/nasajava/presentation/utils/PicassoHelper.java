package com.dzenlab.nasajava.presentation.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class PicassoHelper {

    public static Picasso getPicasso(Context context) {

        try {

            OkHttpClient client = getUnsafeOkHttpClient();

            return new Picasso.Builder(context).downloader(new OkHttp3Downloader(client)).build();

        } catch (Exception ignore) {

            return Picasso.get();
        }
    }

    @SuppressLint("CustomX509TrustManager")
    private static OkHttpClient getUnsafeOkHttpClient()
            throws NoSuchAlgorithmException, KeyManagementException {

        TrustManager[] trustAllCerts = new TrustManager[1];

        trustAllCerts[0] = new X509TrustManager() {

            @SuppressLint("TrustAllX509TrustManager")
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) { }

            @SuppressLint("TrustAllX509TrustManager")
            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) { }

            @Override
            public X509Certificate[] getAcceptedIssuers() {

                return new X509Certificate[0];
            }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");

        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                .hostnameVerifier((s, sslSession) -> true)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }
}

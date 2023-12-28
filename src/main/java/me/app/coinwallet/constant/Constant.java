package me.app.coinwallet.constant;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class Constant {
    public static final OkHttpClient HTTP_CLIENT;
    static {

        final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.followRedirects(false);
        httpClientBuilder.followSslRedirects(true);
        httpClientBuilder.connectTimeout(15, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(15, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(15, TimeUnit.SECONDS);
//        httpClientBuilder.addInterceptor(loggingInterceptor);
        HTTP_CLIENT = httpClientBuilder.build();
    }
}

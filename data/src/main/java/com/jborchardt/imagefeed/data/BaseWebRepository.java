package com.jborchardt.imagefeed.data;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseWebRepository {

    private final String mBaseUrl;
    private final String mClientId;

    public BaseWebRepository(final String baseUrl, final String clientId) {
        mBaseUrl = baseUrl;
        mClientId = clientId;
    }

    protected Retrofit getRetrofit() {
        final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request original = chain.request();

                final String authValue = "Client-ID " + mClientId;

                final Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", authValue);

                final Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        final OkHttpClient okHttpClient = okHttpClientBuilder.build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}

package com.jborchardt.imagefeed.data.feed;

import android.support.annotation.NonNull;

import com.jborchardt.imagefeed.data.model.FeedItem;
import com.jborchardt.imagefeed.data.model.ImgurResponse;
import com.jborchardt.imagefeed.domain.feed.FeedRepository;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by johannesborchardt on 21.03.17.
 */

public class FeedWebRepository implements FeedRepository {

    private static final int DEFAULT_PAGE_SIZE = 100;

    private final ImgurService mImgurService;

    public FeedWebRepository(final String baseUrl, final String clientId) {
        mImgurService = createImgurService(baseUrl, clientId);
    }

    private ImgurService createImgurService(final String baseUrl, final String clientId) {
        final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                final Request original = chain.request();

                final String authValue = "Client-ID " + clientId;

                final Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", authValue);

                final Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        final OkHttpClient okHttpClient = okHttpClientBuilder.build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ImgurService.class);
    }

    @Override
    public List<FeedItem> fetchFeed(@NonNull final int page) throws IOException {
        final Call<ImgurResponse> feedItemsCall = mImgurService.listItems(page, DEFAULT_PAGE_SIZE);
        final retrofit2.Response<ImgurResponse> response = feedItemsCall.execute();
        return response.body().getData();
    }

    private interface ImgurService {
        @GET("gallery/hot/viral/0.json")
        Call<ImgurResponse> listItems(@Query("page") int page, @Query("perPage") int perPage);
    }
}

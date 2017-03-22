package com.jborchardt.imagefeed.data.feed;

import android.support.annotation.NonNull;

import com.jborchardt.imagefeed.data.BaseWebRepository;
import com.jborchardt.imagefeed.data.model.ImgurFeedResponse;
import com.jborchardt.imagefeed.data.model.ImgurImage;
import com.jborchardt.imagefeed.domain.feed.FeedRepository;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by johannesborchardt on 21.03.17.
 */

public class FeedWebRepository extends BaseWebRepository implements FeedRepository {

    private static final int DEFAULT_PAGE_SIZE = 100;

    private final ImgurService mImgurService;
    private boolean mIsFetching;

    public FeedWebRepository(final String baseUrl, final String clientId) {
        super(baseUrl, clientId);

        mImgurService = createImgurService();
    }

    private ImgurService createImgurService() {
        return getRetrofit().create(ImgurService.class);
    }


    public boolean isFetching() {
        return mIsFetching;
    }

    private void setIsFetching(final boolean fetching) {
        mIsFetching = fetching;
    }

    @Override
    public List<ImgurImage> fetchFeed(@NonNull final int page) throws IOException {
        setIsFetching(true);

        try {
            final Call<ImgurFeedResponse> feedItemsCall = mImgurService.fetchListItems(page, DEFAULT_PAGE_SIZE);
            final retrofit2.Response<ImgurFeedResponse> response = feedItemsCall.execute();
            return response.body().getData();
        } finally {
            setIsFetching(false);
        }
    }

    private interface ImgurService {
        @GET("gallery/hot/viral/0.json")
        Call<ImgurFeedResponse> fetchListItems(@Query("page") int page, @Query("perPage") int perPage);
    }
}

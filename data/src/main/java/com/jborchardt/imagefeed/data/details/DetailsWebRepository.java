package com.jborchardt.imagefeed.data.details;

import android.support.annotation.NonNull;

import com.jborchardt.imagefeed.data.BaseWebRepository;
import com.jborchardt.imagefeed.data.model.ImgurImageResponse;
import com.jborchardt.imagefeed.domain.details.DetailsModel;
import com.jborchardt.imagefeed.domain.details.DetailsRepository;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class DetailsWebRepository extends BaseWebRepository implements DetailsRepository {

    private final ImgurService mImgurService;

    public DetailsWebRepository(final String baseUrl, final String clientId) {
        super(baseUrl, clientId);

        mImgurService = createImgurService();
    }

    private ImgurService createImgurService() {
        return getRetrofit().create(ImgurService.class);
    }

    @Override
    public DetailsModel fetchDetails(@NonNull final String id) throws IOException {
        final Call<ImgurImageResponse> detailsCall = mImgurService.fetchDetails(id);
        final retrofit2.Response<ImgurImageResponse> response = detailsCall.execute();

        return response.body().getData();
    }

    private interface ImgurService {
        @GET("gallery/image/{imageId}.json")
        Call<ImgurImageResponse> fetchDetails(@Path("imageId") String imageId);
    }
}

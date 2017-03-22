package com.jborchardt.imagefeed.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by johannesborchardt on 22.03.17.
 */

public class ImgurFeedResponse {
    @SerializedName("data")
    List<ImgurImage> mData;

    public List<ImgurImage> getData() {
        return mData;
    }
}

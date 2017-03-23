package com.jborchardt.imagefeed.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johannesborchardt on 22.03.17.
 */

public class ImgurImageResponse {
    @SerializedName("data")
    private ImgurImage mData;

    public ImgurImage getData() {
        return mData;
    }
}

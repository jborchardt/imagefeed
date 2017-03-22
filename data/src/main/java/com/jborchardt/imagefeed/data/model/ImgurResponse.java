package com.jborchardt.imagefeed.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by johannesborchardt on 22.03.17.
 */

public class ImgurResponse {
    @SerializedName("data")
    List<FeedItem> mData;

    public List<FeedItem> getData() {
        return mData;
    }
}

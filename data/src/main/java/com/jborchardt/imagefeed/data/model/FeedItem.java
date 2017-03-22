package com.jborchardt.imagefeed.data.model;

import com.google.gson.annotations.SerializedName;
import com.jborchardt.imagefeed.domain.feed.FeedItemModel;

/**
 * Created by johannesborchardt on 21.03.17.
 */

public class FeedItem implements FeedItemModel {

    @SerializedName("id")
    private String mId;

    @SerializedName("link")
    private String mImageUrl;

    @SerializedName("type")
    private String mType;

    @Override
    public String getId() {
        return mId;
    }

    @Override
    public String getImageUrl() {
        return mImageUrl;
    }

    @Override
    public String getType() {
        return mType;
    }
}

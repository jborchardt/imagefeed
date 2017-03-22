package com.jborchardt.imagefeed.data.model;

import com.google.gson.annotations.SerializedName;
import com.jborchardt.imagefeed.domain.details.DetailsModel;
import com.jborchardt.imagefeed.domain.feed.FeedItemModel;

public class ImgurImage implements FeedItemModel, DetailsModel {

    @SerializedName("id")
    private String mId;

    @SerializedName("link")
    private String mImageUrl;

    @SerializedName("type")
    private String mType;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("ups")
    private int mUpvotes;

    @SerializedName("downs")
    private int mDownvotes;

    @SerializedName("comment_count")
    private int mComments;

    @SerializedName("views")
    private int mViews;

    @Override
    public String getId() {
        return mId;
    }

    @Override
    public String getImageUrl() {
        return mImageUrl;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public int getUpvotes() {
        return mUpvotes;
    }

    @Override
    public int getDownvotes() {
        return mDownvotes;
    }

    @Override
    public int getComments() {
        return mComments;
    }

    @Override
    public int getViews() {
        return mViews;
    }

    @Override
    public String getType() {
        return mType;
    }
}

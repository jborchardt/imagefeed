package com.jborchardt.imagefeed.domain.details;

public interface DetailsModel {
    String getImageUrl();

    String getTitle();

    int getUpvotes();

    int getDownvotes();

    int getComments();

    int getViews();
}

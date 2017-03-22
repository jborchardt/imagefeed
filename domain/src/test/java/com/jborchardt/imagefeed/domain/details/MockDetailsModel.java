package com.jborchardt.imagefeed.domain.details;

/**
 * Created by johannesborchardt on 22.03.17.
 */

class MockDetailsModel implements DetailsModel {
    @Override
    public String getImageUrl() {
        return "image/url.jpeg";
    }

    @Override
    public String getTitle() {
        return "title";
    }

    @Override
    public int getUpvotes() {
        return 10;
    }

    @Override
    public int getDownvotes() {
        return 1;
    }

    @Override
    public int getComments() {
        return 5;
    }

    @Override
    public int getViews() {
        return 120;
    }
}

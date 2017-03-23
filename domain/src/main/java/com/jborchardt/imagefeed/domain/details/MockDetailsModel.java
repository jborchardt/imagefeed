package com.jborchardt.imagefeed.domain.details;

class MockDetailsModel implements DetailsModel {
    @Override
    public String getImageUrl() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public int getUpvotes() {
        return 0;
    }

    @Override
    public int getDownvotes() {
        return 0;
    }

    @Override
    public int getComments() {
        return 0;
    }

    @Override
    public int getViews() {
        return 0;
    }
}

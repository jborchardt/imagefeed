package com.jborchardt.imagefeed.domain.feed;

/**
 * Created by johannesborchardt on 21.03.17.
 */

class MockFeedItemModelModel implements FeedItemModel {

    private String mImageName;

    MockFeedItemModelModel(final String imageName) {
        mImageName = imageName;
    }

    @Override
    public String getId() {
        return null;
    }

    public String getImageUrl() {
        return mImageName;
    }

    @Override
    public String getType() {
        return null;
    }

}

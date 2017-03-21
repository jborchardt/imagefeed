package com.jborchardt.domain.feed;

import com.jborchardt.domain.feed.FeedItem;

/**
 * Created by johannesborchardt on 21.03.17.
 */

class MockFeedItemModel implements FeedItem {

    private String mImageName;

    MockFeedItemModel(final String imageName) {
        mImageName = imageName;
    }

    public String getImageName() {
        return mImageName;
    }

}

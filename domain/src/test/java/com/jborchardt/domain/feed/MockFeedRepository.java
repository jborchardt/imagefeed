package com.jborchardt.domain.feed;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johannesborchardt on 21.03.17.
 */

public class MockFeedRepository implements FeedRepository {
    public static final int FEED_ITEMS = 10;

    @Override
    public String getAccessToken() {
        return "t35tT0k3n";
    }

    @Override
    public List<FeedItem> fetchFeed(@NonNull final String accessToken) {
        final List<FeedItem> feedItems = new ArrayList<>(FEED_ITEMS);
        for(int i = 0; i < FEED_ITEMS; i++) {
            feedItems.add(new MockFeedItemModel(Integer.toString(i)));
        }
        return feedItems;
    }
}

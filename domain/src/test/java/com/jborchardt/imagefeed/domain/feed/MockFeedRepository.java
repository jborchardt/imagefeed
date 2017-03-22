package com.jborchardt.imagefeed.domain.feed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MockFeedRepository implements FeedRepository {
    public static final int FEED_ITEMS = 10;

    @Override
    public List<? extends FeedItemModel> fetchFeed(final int page) throws IOException {
        final List<FeedItemModel> feedItems = new ArrayList<>(FEED_ITEMS);
        for(int i = 0; i < FEED_ITEMS; i++) {
            feedItems.add(new MockFeedItemModelModel(Integer.toString(i)));
        }
        return feedItems;    }
}

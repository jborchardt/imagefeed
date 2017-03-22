package com.jborchardt.imagefeed.data;

import com.jborchardt.imagefeed.data.feed.FeedWebRepository;
import com.jborchardt.imagefeed.data.model.FeedItem;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestImgurFeed {

    private FeedWebRepository mRepository;

    @Before
    public void setUp() {
        mRepository = new FeedWebRepository("https://api.imgur.com/3/", "f9b40c645077bbb");
    }

    @Test
    public void testFetchFeed() throws IOException {
        final List<FeedItem> feedItems = mRepository.fetchFeed(0);

        Assert.assertNotNull(feedItems);
        Assert.assertTrue(feedItems.size() > 0);
    }
}

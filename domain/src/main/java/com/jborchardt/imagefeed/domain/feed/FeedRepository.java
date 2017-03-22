package com.jborchardt.imagefeed.domain.feed;

import java.io.IOException;
import java.util.List;

/**
 * Created by johannesborchardt on 21.03.17.
 */

public interface FeedRepository {
    List<? extends FeedItemModel> fetchFeed(int page) throws IOException;
}

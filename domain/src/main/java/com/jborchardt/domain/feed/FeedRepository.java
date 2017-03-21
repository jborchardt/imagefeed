package com.jborchardt.domain.feed;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by johannesborchardt on 21.03.17.
 */

public interface FeedRepository {
    String getAccessToken();
    List<FeedItem> fetchFeed(@NonNull String accessToken);

}

package com.jborchardt.imagefeed.data;

import android.content.Context;

import com.jborchardt.imagefeed.data.feed.FeedWebRepository;
import com.jborchardt.imagefeed.domain.feed.FeedRepository;

/**
 * Created by johannesborchardt on 21.03.17.
 */

public class RepositoryRegistry {

    private static RepositoryRegistry sInstance;
    private final Context mContext;
    private FeedWebRepository mFeedRepository;

    private RepositoryRegistry(Context context) {
        mContext = context.getApplicationContext();
    }

    public static synchronized RepositoryRegistry getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new RepositoryRegistry(context);
        }
        return sInstance;
    }

    public FeedRepository getFeedRepository() {
        if(mFeedRepository == null) {
            mFeedRepository = new FeedWebRepository(mContext);
        }
        return mFeedRepository;
    }

}

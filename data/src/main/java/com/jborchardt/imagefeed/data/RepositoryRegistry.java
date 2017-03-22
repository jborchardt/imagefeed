package com.jborchardt.imagefeed.data;

import android.content.Context;

import com.jborchardt.imagefeed.data.details.DetailsWebRepository;
import com.jborchardt.imagefeed.data.feed.FeedWebRepository;
import com.jborchardt.imagefeed.domain.details.DetailsRepository;
import com.jborchardt.imagefeed.domain.feed.FeedRepository;

/**
 * Created by johannesborchardt on 21.03.17.
 */

public class RepositoryRegistry {

    private static RepositoryRegistry sInstance;

    private final Context mContext;
    private FeedWebRepository mFeedRepository;
    private DetailsWebRepository mDetailsRepository;

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
            final String baseUrl = mContext.getString(R.string.base_url);
            final String clientId = mContext.getString(R.string.client_id);
            mFeedRepository = new FeedWebRepository(baseUrl, clientId);
        }
        return mFeedRepository;
    }

    public DetailsRepository getDetailsRepository() {
        if(mDetailsRepository == null) {
            final String baseUrl = mContext.getString(R.string.base_url);
            final String clientId = mContext.getString(R.string.client_id);
            mDetailsRepository = new DetailsWebRepository(baseUrl, clientId);
        }
        return mDetailsRepository;
    }

}

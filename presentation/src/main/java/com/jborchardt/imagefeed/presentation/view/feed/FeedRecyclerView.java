package com.jborchardt.imagefeed.presentation.view.feed;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by johannesborchardt on 21.03.17.
 */

public class FeedRecyclerView extends RecyclerView {

    public FeedRecyclerView(final Context context) {
        this(context, null);
    }

    public FeedRecyclerView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FeedRecyclerView(final Context context, @Nullable final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        setLayoutManager(new LinearLayoutManager(getContext()));
    }
}

package com.jborchardt.imagefeed.presentation.view.feed;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.jborchardt.imagefeed.presentation.R;


public class FeedRecyclerView extends RecyclerView {

    private final float mMinItemWidth;
    private final GridLayoutManager mGridLayoutManager;
    private final FeedItemDecoration mItemDecoration;

    private final float mItemMargin;

    public FeedRecyclerView(final Context context) {
        this(context, null);
    }

    public FeedRecyclerView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FeedRecyclerView(final Context context, @Nullable final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        mMinItemWidth = getResources().getDimension(R.dimen.feed_item_min_width);
        mGridLayoutManager = new GridLayoutManager(getContext(), 1);
        setLayoutManager(mGridLayoutManager);

        mItemMargin = getResources().getDimension(R.dimen.feed_item_margin);
        mItemDecoration = new FeedItemDecoration((int) mItemMargin, (int) mItemMargin, true, true, 1);

        addItemDecoration(mItemDecoration);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);

        int columnCount = (int) Math.ceil(w / (mMinItemWidth + mItemMargin));
        mItemDecoration.setColumnCount(columnCount);
        mGridLayoutManager.setSpanCount(columnCount);

        invalidateItemDecorations();
    }
}

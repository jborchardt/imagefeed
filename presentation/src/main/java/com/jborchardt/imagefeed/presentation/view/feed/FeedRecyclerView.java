package com.jborchardt.imagefeed.presentation.view.feed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.jborchardt.imagefeed.presentation.R;


public class FeedRecyclerView extends RecyclerView {

    private static final int ITEMS_BEFORE_NEXT_PAGE = 2;

    private final GridLayoutManager mGridLayoutManager;
    private final FeedItemDecoration mItemDecoration;

    private final float mMinItemWidth;
    private final float mItemMargin;

    private OnEndReachedListener mOnEndReachedListener;

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

        addOnScrollListener(new PageableScrollListener());
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);

        int columnCount = (int) Math.ceil(w / (mMinItemWidth + mItemMargin));
        mItemDecoration.setColumnCount(columnCount);
        mGridLayoutManager.setSpanCount(columnCount);

        invalidateItemDecorations();
    }

    void setOnEndReachedListener(@NonNull final OnEndReachedListener onEndReachedListener) {
        mOnEndReachedListener = onEndReachedListener;
    }

    private class PageableScrollListener extends OnScrollListener {

        @Override
        public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
            int lastItem = mGridLayoutManager.findLastVisibleItemPosition();

            if (lastItem > getAdapter().getItemCount() - ITEMS_BEFORE_NEXT_PAGE && mOnEndReachedListener != null) {
                mOnEndReachedListener.onEndReached();
            }
        }
    }

    interface OnEndReachedListener {
        void onEndReached();
    }
}

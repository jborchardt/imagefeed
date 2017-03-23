package com.jborchardt.imagefeed.presentation.view.feed;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class FeedItemDecoration extends RecyclerView.ItemDecoration {
    private final int     mVerticalMargin;
    private final int     mHorizontalMargin;
    private final boolean mUseHorizontalMarginOnEdges;
    private final boolean mUseVerticalMarginOnEdges;
    private       int     mColumnCount;

    public FeedItemDecoration(int horizontalMargin, int verticalMargin, boolean useHorizontalMarginOnEdges, boolean useVerticalMarginOnEdges, int initialColumnCount) {
        mVerticalMargin = verticalMargin;
        mHorizontalMargin = (int) (horizontalMargin / 2f);
        mUseHorizontalMarginOnEdges = useHorizontalMarginOnEdges;
        mUseVerticalMarginOnEdges = useVerticalMarginOnEdges;
        setColumnCount(initialColumnCount);
    }

    public void setColumnCount(int columnCount) {
        mColumnCount = Math.max(columnCount, 1);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (mUseVerticalMarginOnEdges || position >= mColumnCount) {
            outRect.top = mVerticalMargin;
        }
        int maxRows = parent.getAdapter().getItemCount() / mColumnCount;
        if (parent.getAdapter().getItemCount() % mColumnCount > 0) {
            maxRows++;
        }
        if (mUseVerticalMarginOnEdges && position / mColumnCount == maxRows - 1) {
            outRect.bottom = mVerticalMargin;
        }

        if (mUseHorizontalMarginOnEdges || position % mColumnCount != 0) {
            outRect.left = mHorizontalMargin;
        }
        if (mUseHorizontalMarginOnEdges || (position + 1) % mColumnCount != 0) {
            outRect.right = mHorizontalMargin;
        }
    }
}
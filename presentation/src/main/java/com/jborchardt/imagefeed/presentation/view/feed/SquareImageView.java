package com.jborchardt.imagefeed.presentation.view.feed;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class SquareImageView extends AppCompatImageView {
    public SquareImageView(final Context context) {
        super(context);
    }

    public SquareImageView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}

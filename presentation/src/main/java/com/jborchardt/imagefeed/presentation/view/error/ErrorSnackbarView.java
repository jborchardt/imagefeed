package com.jborchardt.imagefeed.presentation.view.error;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.jborchardt.imagefeed.presentation.R;
import com.jborchardt.imagefeed.presentation.view.common.ErrorView;


@SuppressWarnings({"unchecked", "rawtypes"})
// This module must be able to handle observables of several types.
public class ErrorSnackbarView implements ErrorView {

    private final View mView;
    private Snackbar mSnackbar;
    private View.OnClickListener mOnClickListener;

    public ErrorSnackbarView(View parent) {
        mView = parent;
    }

    public void setOnClickListener(@Nullable final View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @Override
    public void showError(final boolean showRetry) {
        mSnackbar = createSnackbar(showRetry);
        mSnackbar.show();
    }

    /* Helpers */

    private Snackbar createSnackbar(final boolean showRetry) {
        final Snackbar snackbar;

        if (showRetry && mOnClickListener != null) {
            snackbar = Snackbar.make(mView, mView.getContext().getString(R.string.error_retry), Snackbar.LENGTH_INDEFINITE)
                    .setAction(mView.getContext().getString(R.string.retry), mOnClickListener);
        } else {
            snackbar = Snackbar.make(mView, mView.getContext().getString(R.string.error_default), Snackbar.LENGTH_LONG);
        }

        return snackbar;
    }
}
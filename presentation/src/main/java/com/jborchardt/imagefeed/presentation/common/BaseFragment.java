package com.jborchardt.imagefeed.presentation.common;

import android.support.v4.app.Fragment;

import com.jborchardt.imagefeed.presentation.view.error.ErrorSnackbarView;
import com.jborchardt.imagefeed.presentation.view.loading.LoadingViewProvider;

public abstract class BaseFragment extends Fragment implements LoadingView, ErrorView {

    private LoadingView getLoadingView() {
        return ((LoadingViewProvider) getActivity()).provideLoadingView();
    }

    @Override
    public void showLoading() {
        getLoadingView().showLoading();
    }

    @Override
    public void hideLoading() {
        getLoadingView().hideLoading();
    }

    public void showError(final boolean showRetry) {
        final ErrorSnackbarView errorView = new ErrorSnackbarView(getView());
        errorView.setOnClickListener(view -> retryClicked());
        errorView.showError(showRetry);
    }

    protected abstract void retryClicked();
}

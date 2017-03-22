package com.jborchardt.imagefeed.presentation.common;

import android.support.v4.app.Fragment;

import com.jborchardt.imagefeed.presentation.view.loading.LoadingViewProvider;

public class BaseFragment extends Fragment implements LoadingView {

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

}

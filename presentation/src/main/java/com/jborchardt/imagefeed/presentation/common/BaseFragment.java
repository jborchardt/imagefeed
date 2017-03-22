package com.jborchardt.imagefeed.presentation.common;

import android.support.v4.app.Fragment;

import com.jborchardt.imagefeed.presentation.view.toolbar.LoadingViewProvider;

/**
 * Created by johannesborchardt on 22.03.17.
 */

public class BaseFragment extends Fragment implements LoadingView, ErrorView {

    private LoadingView getLoadingView() {
        return ((LoadingViewProvider) getActivity()).getLoadingView();
    }

    @Override
    public void showLoading() {
        getLoadingView().showLoading();
    }

    @Override
    public void hideLoading() {
        getLoadingView().hideLoading();
    }

    @Override
    public void showError() {
        //TODO
    }
}

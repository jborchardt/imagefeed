package com.jborchardt.imagefeed.presentation.presenter.details;

import android.support.annotation.NonNull;

import com.jborchardt.imagefeed.domain.details.DetailsInteractor;
import com.jborchardt.imagefeed.domain.details.DetailsModel;
import com.jborchardt.imagefeed.presentation.presenter.common.Presenter;
import com.jborchardt.imagefeed.presentation.view.common.BaseObserver;

public class DetailsPresenter implements Presenter {

    private final DetailsInteractor mInteractor;
    private final DetailsView mView;
    private final String mImageId;
    private boolean mMetadataVisible;

    public DetailsPresenter(@NonNull final DetailsInteractor interactor, @NonNull DetailsView view, @NonNull String imageId) {
        mInteractor = interactor;
        mView = view;
        mImageId = imageId;
    }

    @Override
    public void register() {
        showLoading();
        fetchDetails();
    }

    @Override
    public void unregister() {
    }

    @Override
    public void destroy() {
        mInteractor.dispose();
    }

    public void retry() {
        fetchDetails();
    }

    private void fetchDetails() {
        mInteractor.fetchDetails(new DetailsObserver(), mImageId);
    }

    private void showDetails(final DetailsModel details) {
        mView.renderDetails(details);
    }

    private void showLoading() {
        mView.showLoading();
    }

    private void hideLoading() {
        mView.hideLoading();
    }

    private void showError(final boolean showRetry) {
        mView.showError(showRetry);
    }

    public void toggleMetadata() {
        if (mMetadataVisible) {
            mView.hideMetadata();
            mMetadataVisible = false;
        } else {
            mView.showMetadata();
            mMetadataVisible = true;
        }
    }

    private class DetailsObserver extends BaseObserver<DetailsModel> {

        @Override
        public void onNext(final DetailsModel details) {
            showDetails(details);
        }

        @Override
        public void onComplete() {
            hideLoading();
        }

        @Override
        public void onError(final Throwable e) {
            hideLoading();
            showError(shouldRetry(e));
        }
    }
}

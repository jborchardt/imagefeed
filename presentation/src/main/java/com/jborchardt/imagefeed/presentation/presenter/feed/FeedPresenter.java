package com.jborchardt.imagefeed.presentation.presenter.feed;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jborchardt.imagefeed.domain.feed.FeedInteractor;
import com.jborchardt.imagefeed.domain.feed.FeedItemModel;
import com.jborchardt.imagefeed.presentation.R;
import com.jborchardt.imagefeed.presentation.common.BaseObserver;
import com.jborchardt.imagefeed.presentation.common.Presenter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class utilizes the presenter-framework provided by {@link RecyclerView}.
 */

public class FeedPresenter extends RecyclerView.Adapter<FeedPresenter.FeedViewHolder> implements Presenter {

    private final FeedView mView;
    private final FeedInteractor mInteractor;
    private final List<FeedItemModel> mFeedItems;

    public FeedPresenter(@NonNull final FeedInteractor interactor, @NonNull FeedView view) {
        mInteractor = interactor;
        mView = view;
        mFeedItems = new ArrayList<>();
    }

    @Override
    public void register() {
        showLoading();
        fetchFirstPage();
    }

    public void retry() {
        fetchFirstPage();
    }

    private void fetchFirstPage() {
        mInteractor.fetchFirstFeedPage(new FeedObserver());
    }

    @Override
    public void unregister() {
        mInteractor.dispose();
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

    @Override
    public FeedViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final ImageView itemView = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FeedViewHolder holder, final int position) {
        final FeedItemModel item = mFeedItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mFeedItems.size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageView;

        public FeedViewHolder(final ImageView itemView) {
            super(itemView);

            mImageView = itemView;
        }

        public void bind(final FeedItemModel item) {
            Glide
                    .with(mImageView.getContext())
                    .load(item.getImageUrl())
                    .into(mImageView);
        }
    }

    private class FeedObserver extends BaseObserver<FeedItemModel> {

        @Override
        public void onNext(final FeedItemModel item) {
            mFeedItems.add(item);
            notifyDataSetChanged();
        }

        @Override
        public void onComplete() {
            hideLoading();
        }

        @Override
        public void onError(final Throwable e) {
            showError(shouldRetry(e));
        }
    }
}

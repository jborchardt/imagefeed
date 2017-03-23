package com.jborchardt.imagefeed.presentation.view.feed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jborchardt.imagefeed.data.RepositoryRegistry;
import com.jborchardt.imagefeed.domain.feed.FeedInteractor;
import com.jborchardt.imagefeed.domain.feed.FeedRepository;
import com.jborchardt.imagefeed.presentation.R;
import com.jborchardt.imagefeed.presentation.view.common.BaseFragment;
import com.jborchardt.imagefeed.presentation.presenter.feed.FeedPresenter;
import com.jborchardt.imagefeed.presentation.presenter.feed.FeedView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FeedFragment extends BaseFragment implements FeedView {

    private FeedRecyclerView mFeedRecyclerView;
    private FeedPresenter mFeedPresenter;

    @Deprecated
    public FeedFragment() {
        // Required empty public constructor
    }

    public static FeedFragment newInstance() {
        @SuppressWarnings("deprecation")
        final FeedFragment fragment = new FeedFragment();

        return fragment;
    }

    @Nullable
    @Override
    public android.view.View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, final Bundle savedInstanceState) {
        mFeedRecyclerView = (FeedRecyclerView) inflater.inflate(R.layout.fragment_feed, container, false);

        setUpPresenter();
        setUpViews();

        return mFeedRecyclerView;
    }

    private void setUpPresenter() {
        final FeedRepository feedRepository = RepositoryRegistry.getInstance(getActivity()).getFeedRepository();
        final FeedInteractor feedInteractor = new FeedInteractor(Schedulers.io(), AndroidSchedulers.mainThread(), feedRepository);
        mFeedPresenter = new FeedPresenter(feedInteractor, getNavigator(), this);
    }

    private void setUpViews() {
        mFeedRecyclerView.setAdapter(mFeedPresenter);
        mFeedRecyclerView.setOnEndReachedListener(new FetchNextPageListener());
    }

    @Override
    public void onStart() {
        super.onStart();

        mFeedPresenter.register();
    }

    @Override
    public void onStop() {
        mFeedPresenter.unregister();

        super.onStop();
    }

    @Override
    protected void onRetry() {
        mFeedPresenter.retry();
    }

    private class FetchNextPageListener implements FeedRecyclerView.OnEndReachedListener {

        @Override
        public void onEndReached() {
            mFeedPresenter.fetchNextPage();
        }
    }
}

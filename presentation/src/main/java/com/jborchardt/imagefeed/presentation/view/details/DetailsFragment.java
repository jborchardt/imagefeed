package com.jborchardt.imagefeed.presentation.view.details;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jborchardt.imagefeed.data.RepositoryRegistry;
import com.jborchardt.imagefeed.domain.details.DetailsInteractor;
import com.jborchardt.imagefeed.domain.details.DetailsModel;
import com.jborchardt.imagefeed.domain.details.DetailsRepository;
import com.jborchardt.imagefeed.presentation.R;
import com.jborchardt.imagefeed.presentation.presenter.details.DetailsPresenter;
import com.jborchardt.imagefeed.presentation.presenter.details.DetailsView;
import com.jborchardt.imagefeed.presentation.view.common.BaseFragment;
import com.jborchardt.imagefeed.presentation.view.common.LoadingView;
import com.jborchardt.imagefeed.presentation.view.loading.LoadingCircleView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailsFragment extends BaseFragment implements DetailsView {

    private static final String ARG_ID = "id";

    private static final int UI_ANIMATION_DELAY = 300;

    private String mId;
    private DetailsPresenter mDetailsPresenter;

    private View mContentView;
    private View mMetaDataView;
    private LoadingCircleView mLoadingView;
    private ImageView mImageView;
    private TextView mTitleView;
    private TextView mUpvotesView;
    private TextView mDownvotesView;
    private TextView mCommentsView;
    private TextView mViewsView;

    private final Handler mHideHandler = new Handler();
    private final Runnable mHideRunnable = () -> {
        hideMetadata();
    };

    private final Runnable mHidePart2Runnable = () -> {
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    };
    private final Runnable mShowPart2Runnable = () -> {
        mMetaDataView.setVisibility(View.VISIBLE);
    };

    @Deprecated
    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(@NonNull final String id) {
        @SuppressWarnings("deprecation")
        final DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mId = getArguments().getString(ARG_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_details, container, false);

        setUpPresenter();
        setUpViews(view);

        return view;
    }

    private void setUpPresenter() {
        final DetailsRepository detailsRepository = RepositoryRegistry.getInstance(getActivity()).getDetailsRepository();
        final DetailsInteractor detailsInteractor = new DetailsInteractor(Schedulers.io(), AndroidSchedulers.mainThread(), detailsRepository);
        mDetailsPresenter = new DetailsPresenter(detailsInteractor, this, mId);
    }

    private void setUpViews(final View view) {
        mContentView = view.findViewById(R.id.content);
        mMetaDataView = view.findViewById(R.id.meta_data);
        mLoadingView = (LoadingCircleView) view.findViewById(R.id.loading);
        mImageView = (ImageView) view.findViewById(R.id.image);
        mTitleView = (TextView) view.findViewById(R.id.title);
        mUpvotesView = (TextView) view.findViewById(R.id.upvotes);
        mDownvotesView = (TextView) view.findViewById(R.id.downvotes);
        mCommentsView = (TextView) view.findViewById(R.id.comments);
        mViewsView = (TextView) view.findViewById(R.id.views);

        mContentView.setOnClickListener(v -> {
            toggleMetadata();
        });
    }

    private void toggleMetadata() {
        mDetailsPresenter.toggleMetadata();
    }

    @Override
    protected LoadingView getLoadingView() {
        return mLoadingView;
    }

    @Override
    public void onStart() {
        super.onStart();

        mDetailsPresenter.register();
    }

    @Override
    public void onStop() {
        mDetailsPresenter.unregister();

        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();

        delayedHide(100);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    public void hideMetadata() {
        mMetaDataView.setVisibility(View.GONE);

        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @Override
    public void showMetadata() {
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    @Override
    protected void onRetry() {
        mDetailsPresenter.retry();
    }

    @Override
    public void renderDetails(final DetailsModel details) {
        Glide
                .with(getActivity())
                .load(details.getImageUrl())
                .into(mImageView);

        mTitleView.setText(details.getTitle());
        mUpvotesView.setText(Integer.toString(details.getUpvotes()));
        mDownvotesView.setText(Integer.toString(details.getDownvotes()));
        mCommentsView.setText(Integer.toString(details.getComments()));
        mViewsView.setText(Integer.toString(details.getViews()));
    }
}

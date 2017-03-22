package com.jborchardt.imagefeed.presentation.view.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jborchardt.imagefeed.data.RepositoryRegistry;
import com.jborchardt.imagefeed.domain.details.DetailsInteractor;
import com.jborchardt.imagefeed.domain.details.DetailsModel;
import com.jborchardt.imagefeed.domain.details.DetailsRepository;
import com.jborchardt.imagefeed.presentation.R;
import com.jborchardt.imagefeed.presentation.common.BaseFragment;
import com.jborchardt.imagefeed.presentation.presenter.details.DetailsPresenter;
import com.jborchardt.imagefeed.presentation.presenter.details.DetailsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailsFragment extends BaseFragment implements DetailsView {

    private static final String ARG_ID = "id";

    private DetailsPresenter mDetailsPresenter;
    private String mId;

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
    public android.view.View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, final Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_feed, container, false);

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
    protected void retryClicked() {
        mDetailsPresenter.retry();
    }

    @Override
    public void renderDetails(final DetailsModel details) {

    }
}

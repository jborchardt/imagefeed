package com.jborchardt.imagefeed.presentation.presenter.details;

import com.jborchardt.imagefeed.domain.details.DetailsModel;
import com.jborchardt.imagefeed.presentation.view.common.ErrorView;
import com.jborchardt.imagefeed.presentation.view.common.LoadingView;
import com.jborchardt.imagefeed.presentation.view.common.View;

public interface DetailsView extends View, LoadingView, ErrorView{

    void renderDetails(DetailsModel details);

    void hideMetadata();

    void showMetadata();
}

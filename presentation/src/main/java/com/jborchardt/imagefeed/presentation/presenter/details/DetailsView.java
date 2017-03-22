package com.jborchardt.imagefeed.presentation.presenter.details;

import com.jborchardt.imagefeed.domain.details.DetailsModel;
import com.jborchardt.imagefeed.presentation.common.ErrorView;
import com.jborchardt.imagefeed.presentation.common.LoadingView;
import com.jborchardt.imagefeed.presentation.common.View;

public interface DetailsView extends View, LoadingView, ErrorView{

    void renderDetails(DetailsModel details);
}

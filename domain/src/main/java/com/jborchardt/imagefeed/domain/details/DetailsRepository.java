package com.jborchardt.imagefeed.domain.details;

import android.support.annotation.NonNull;

public interface DetailsRepository {
    DetailsModel fetchDetails(@NonNull final String id);
}

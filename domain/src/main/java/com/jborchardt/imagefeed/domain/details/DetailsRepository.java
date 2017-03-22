package com.jborchardt.imagefeed.domain.details;

import android.support.annotation.NonNull;

import java.io.IOException;

public interface DetailsRepository {
    DetailsModel fetchDetails(@NonNull final String id) throws IOException;
}

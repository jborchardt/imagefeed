package com.jborchardt.imagefeed.domain.details;

import java.io.IOException;

import io.reactivex.annotations.NonNull;

public interface DetailsRepository {
    DetailsModel fetchDetails(@NonNull final String id) throws IOException;
}

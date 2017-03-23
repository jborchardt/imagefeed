package com.jborchardt.imagefeed.domain.details;

import io.reactivex.annotations.NonNull;

public class MockDetailsRepository implements DetailsRepository {

    private final boolean mShouldFail;

    MockDetailsRepository(boolean shouldFail) {
        mShouldFail = shouldFail;
    }

    @Override
    public DetailsModel fetchDetails(@NonNull final String id) {
        if(mShouldFail) {
            throw new RuntimeException();
        }
        return new MockDetailsModel();
    }
}

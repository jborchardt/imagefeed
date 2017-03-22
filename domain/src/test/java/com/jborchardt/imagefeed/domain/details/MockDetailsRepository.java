package com.jborchardt.imagefeed.domain.details;

import android.support.annotation.NonNull;

/**
 * Created by johannesborchardt on 22.03.17.
 */

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

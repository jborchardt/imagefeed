package com.jborchardt.imagefeed.domain.details;

import org.junit.Test;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

public class DetailsInteractorTest {

    @Test
    public void testGetDetails() {
        final DisposableObserver<DetailsModel> observer = new DisposableObserver<DetailsModel>() {

            @Override
            public void onNext(final DetailsModel details) {
                assertNotNull(details);
            }

            @Override
            public void onError(final Throwable e) {
                fail();
            }

            @Override
            public void onComplete() {

            }
        };

        getDetailsInteractor(false).fetchDetails("id", observer);
    }

    @Test
    public void testGetDetailsError() {

        final DisposableObserver<DetailsModel> observer = new DisposableObserver<DetailsModel>() {

            @Override
            public void onNext(final DetailsModel details) {
                fail();
            }

            @Override
            public void onError(final Throwable e) {
                assertNotNull(e);
            }

            @Override
            public void onComplete() {
                fail();
            }
        };

        getDetailsInteractor(true).fetchDetails("id", observer);
    }

    private DetailsInteractor getDetailsInteractor(final boolean shouldThrowError) {
        final DetailsRepository repository = new MockDetailsRepository(shouldThrowError);
        final DetailsInteractor detailsInteractor = new DetailsInteractor(Schedulers.io(), Schedulers.io(), repository);

        return detailsInteractor;
    }
}

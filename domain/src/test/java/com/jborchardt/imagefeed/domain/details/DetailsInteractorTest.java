package com.jborchardt.imagefeed.domain.details;

import org.junit.Test;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

public class DetailsInteractorTest {

    @Test
    public void testGetDetails() {
        final DetailsRepository repository = new MockDetailsRepository(false);

        final DetailsInteractor detailsInteractor = new DetailsInteractor(Schedulers.io(), Schedulers.io(), repository);

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

        detailsInteractor.fetchDetails("id", observer);
    }

    @Test
    public void testGetDetailsError() {
        final DetailsRepository repository = new MockDetailsRepository(true);

        final DetailsInteractor detailsInteractor = new DetailsInteractor(Schedulers.io(), Schedulers.io(), repository);

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

        detailsInteractor.fetchDetails("id", observer);
    }
}

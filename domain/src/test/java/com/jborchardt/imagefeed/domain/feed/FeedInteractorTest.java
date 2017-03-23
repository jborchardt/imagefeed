package com.jborchardt.imagefeed.domain.feed;

import org.junit.Test;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

public class FeedInteractorTest {

    @Test
    public void testGetFeedItems() {
        final DisposableObserver<FeedItemModel> observer = new DisposableObserver<FeedItemModel>() {
            private int mItemCount;

            @Override
            public void onNext(final FeedItemModel value) {
                assertNotNull(value);
                mItemCount++;
            }

            @Override
            public void onError(final Throwable e) {
                fail();
            }

            @Override
            public void onComplete() {
                assertEquals(MockFeedRepository.FEED_ITEMS, mItemCount);
            }
        };

        getFeedInteractor(false).fetchNextPage(observer);
    }

    @Test
    public void testGetFeedItemsError() {
        final DisposableObserver<FeedItemModel> observer = new DisposableObserver<FeedItemModel>() {

            @Override
            public void onNext(final FeedItemModel value) {
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

        getFeedInteractor(true).fetchNextPage(observer);
    }

    @NonNull
    private FeedInteractor getFeedInteractor(final boolean shouldFail) {
        final FeedRepository repository = new MockFeedRepository(shouldFail);
        final FeedInteractor feedInteractor = new FeedInteractor(Schedulers.io(), Schedulers.io(), repository);

        return feedInteractor;
    }


}
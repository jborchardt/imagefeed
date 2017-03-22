package com.jborchardt.imagefeed.domain.feed;

import org.junit.Test;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.*;

public class FeedInteractorTest {

    @Test
    public void feed_results_arrive() throws Exception {
        final FeedRepository repository = new MockFeedRepository();

        final FeedInteractor feedInteractor = new FeedInteractor(Schedulers.io(), Schedulers.io(), repository);

        final DisposableObserver<FeedItemModel> observer = new DisposableObserver<FeedItemModel>() {
            private int mItemCount;

            @Override
            public void onNext(final FeedItemModel value) {
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

        feedInteractor.fetchFirstFeedPage(observer);
    }
}
package com.jborchardt.domain.feed;

import android.support.annotation.NonNull;

import com.jborchardt.domain.common.Interactor;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by johannesborchardt on 21.03.17.
 */

public class FeedInteractor extends Interactor<FeedItem> {

    private final FeedRepository mFeedRepository;

    public FeedInteractor(final Scheduler executionScheduler, final Scheduler postExecutionScheduler, final FeedRepository feedRepository) {
        super(executionScheduler, postExecutionScheduler);

        mFeedRepository = feedRepository;
    }

    public void fetchFeed(@NonNull final DisposableObserver<FeedItem> observer) {
        final Observable<FeedItem> feedObservable = Observable.create(emitter -> {
            try {
                final String accessToken = mFeedRepository.getAccessToken();
                final List<FeedItem> feedItems = mFeedRepository.fetchFeed(accessToken);

                for(FeedItem feedItem : feedItems) {
                    emitter.onNext(feedItem);
                }

                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });

        execute(observer, feedObservable);
    }
}

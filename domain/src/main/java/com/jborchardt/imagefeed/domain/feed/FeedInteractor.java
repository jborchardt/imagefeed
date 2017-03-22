package com.jborchardt.imagefeed.domain.feed;


import com.jborchardt.imagefeed.domain.common.Interactor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class FeedInteractor extends Interactor<FeedItemModel> {

    private String MIME_TYPE_JPEG = "image/jpeg";
    private String MIME_TYPE_PNG = "image/png";

    private final FeedRepository mFeedRepository;
    private int mPage;
    private List<FeedItemModel> mFeedItems;

    public FeedInteractor(@NonNull final Scheduler executionScheduler, @NonNull final Scheduler postExecutionScheduler, @NonNull final FeedRepository feedRepository) {
        super(executionScheduler, postExecutionScheduler);

        mFeedRepository = feedRepository;
        mFeedItems = new ArrayList<>();
    }

    public void fetchNextPage(@NonNull final DisposableObserver<FeedItemModel> observer) {
        fetchFeed(observer, mPage);
        mPage++;
    }

    private void fetchFeed(@NonNull final DisposableObserver<FeedItemModel> observer, final int page) {
        if(mFeedRepository.isFetching()) {
            return;
        }

        final Observable<FeedItemModel> feedObservable = Observable.create(emitter -> {
            try {
                final List<? extends FeedItemModel> feedItems = mFeedRepository.fetchFeed(page);
                for(FeedItemModel feedItem : feedItems) {
                    if(isAcceptedType(feedItem)) {
                        emitter.onNext(feedItem);
                    }
                }

                emitter.onComplete();
            } catch (Exception e) {
                mPage--; // decrease the page index, so that the right page is loaded next

                emitter.onError(e);
            }
        });

        execute(observer, feedObservable);
    }

    private boolean isAcceptedType(final FeedItemModel feedItem) {
        return feedItem != null && (MIME_TYPE_JPEG.equals(feedItem.getType()) || MIME_TYPE_PNG.equals(feedItem.getType()));
    }
}

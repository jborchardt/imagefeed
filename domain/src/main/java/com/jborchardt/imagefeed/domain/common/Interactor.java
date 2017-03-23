package com.jborchardt.imagefeed.domain.common;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * A class extending Interactor is responsible for business logic and must stay free of platform dependent code.
 */

public abstract class Interactor<T> {
    private final Scheduler mExecutionScheduler;
    private final Scheduler mPostExecutionScheduler;

    private CompositeDisposable mDisposables;

    public Interactor(Scheduler executionScheduler, Scheduler postExecutionScheduler) {
        mExecutionScheduler = executionScheduler;
        mPostExecutionScheduler = postExecutionScheduler;
        mDisposables = new CompositeDisposable();
    }

    protected final void execute(@NonNull final Observer<T> observer, @Nullable final Disposable disposable, @NonNull final Observable<T> observable) {
        if(mExecutionScheduler != null && mPostExecutionScheduler != null) {
            registerObserver(observer, disposable, observable);
        } else {
            registerObserverForTesting(observer, observable);
        }
    }

    private void registerObserver(final @NonNull Observer<T> observer, final @Nullable Disposable disposable, final @NonNull Observable<T> observable) {
        observable.subscribeOn(mExecutionScheduler)
                .observeOn(mPostExecutionScheduler)
                .subscribe(observer);

        if (disposable != null) {
            mDisposables.add(disposable);
        }
    }

    private void registerObserverForTesting(final Observer<T> observer, final Observable<T> observable) {
        observable.subscribe(observer);
    }

    public final void dispose() {
        if (mDisposables != null) {
            mDisposables.dispose();
        }
    }
}


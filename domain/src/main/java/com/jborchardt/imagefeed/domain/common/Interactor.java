package com.jborchardt.imagefeed.domain.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A class extending Interactor is responsible for business logic. It is expected that this class stays
 * free of platform dependent code.
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

    protected final void execute(@NonNull DisposableObserver<T> observer, @NonNull Observable<T> observable) {
        mDisposables.add(
                observable.subscribeOn(mExecutionScheduler)
                        .observeOn(mPostExecutionScheduler)
                        .subscribeWith(observer));
    }

    public final void dispose() {
        if (mDisposables != null) {
            mDisposables.dispose();
        }
    }

    /**
     * Provide a resolution when the call fails and a retry-behavior is expected
     */
    @Nullable
    protected Resolution getResolution() {
        return null;
    }
}


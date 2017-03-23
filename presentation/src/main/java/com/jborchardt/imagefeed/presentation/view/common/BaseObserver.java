package com.jborchardt.imagefeed.presentation.view.common;

import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by johannesborchardt on 21.03.17.
 */

public class BaseObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(final T value) {

    }

    @Override
    public void onError(final Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    protected boolean shouldRetry(final Throwable e) {
        return e instanceof UnknownHostException;
    }
}

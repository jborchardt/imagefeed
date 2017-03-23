package com.jborchardt.imagefeed;

import android.app.Application;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

public class ImageFeedApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull final Throwable throwable) throws Exception {
                // Handle all the throwables RxJava could not deliver.
                // Somewhat unsatisfying, they are ignored right now.
                throwable.printStackTrace();
            }
        });

    }
}

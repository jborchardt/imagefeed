package com.jborchardt.imagefeed.domain.details;

import com.jborchardt.imagefeed.domain.common.Interactor;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;

public class DetailsInteractor extends Interactor {
    private final DetailsRepository mDetailsRepository;

    public DetailsInteractor(@NonNull final Scheduler executionScheduler, @NonNull final Scheduler postExecutionScheduler, @NonNull final DetailsRepository detailsRepository) {
        super(executionScheduler, postExecutionScheduler);

        mDetailsRepository = detailsRepository;
    }

    public void fetchDetails(@NonNull final Observer<DetailsModel> observer, @Nullable final Disposable disposable, @NonNull final String id) {
        final Observable<DetailsModel> detailsObservable = Observable.create(emitter -> {
            try {
                final DetailsModel details = mDetailsRepository.fetchDetails(id);
                emitter.onNext(details);

                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });

        execute(observer, disposable, detailsObservable);
    }
}

package com.jborchardt.imagefeed.domain.feed;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class FeedInteractorTest {

    @Mock
    private FeedRepository mFeedRepository;
    private FeedInteractor mFeedInteractor;
    TestObserver<FeedItemModel> mTestObserver;

    @Before
    public void setUp() {
        mFeedInteractor = new FeedInteractor(null, null, mFeedRepository);
        mTestObserver = new TestObserver<>();
    }

    @Test
    public void testFetchFeed() throws IOException {
        mFeedInteractor.fetchNextPage(mTestObserver, null);

        mTestObserver.assertNoErrors();
        verify(mFeedRepository).fetchFeed(0);
        verify(mFeedRepository).isFetching();
        verifyNoMoreInteractions(mFeedRepository);
    }
}
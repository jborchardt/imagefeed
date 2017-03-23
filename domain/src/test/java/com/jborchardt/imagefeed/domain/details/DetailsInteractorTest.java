package com.jborchardt.imagefeed.domain.details;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailsInteractorTest {

    @Mock
    private DetailsRepository mDetailsRepository;
    private DetailsInteractor mDetailsInteractor;
    private TestObserver<DetailsModel> mTestObserver;

    @Before
    public void setUp() {
        mDetailsInteractor = new DetailsInteractor(null, null, mDetailsRepository);
        mTestObserver = new TestObserver<>();
    }

    @Test
    public void testFetchDetails() throws IOException {
        final String id = "testid";
        when(mDetailsRepository.fetchDetails(id)).thenReturn(new MockDetailsModel());

        mDetailsInteractor.fetchDetails(mTestObserver, null, id);

        mTestObserver.assertNoErrors();
        verify(mDetailsRepository).fetchDetails(id);
        verifyNoMoreInteractions(mDetailsRepository);
    }
}

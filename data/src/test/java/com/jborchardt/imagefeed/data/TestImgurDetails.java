package com.jborchardt.imagefeed.data;

import com.jborchardt.imagefeed.data.details.DetailsWebRepository;
import com.jborchardt.imagefeed.domain.details.DetailsModel;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestImgurDetails {

    private DetailsWebRepository mRepository;

    @Before
    public void setUp() {
        mRepository = new DetailsWebRepository("https://api.imgur.com/3/", "f9b40c645077bbb");
    }

    @Test
    public void testFetchDetails() throws IOException {
        final DetailsModel details = mRepository.fetchDetails("cPvREea");

        Assert.assertNotNull(details);
    }
}

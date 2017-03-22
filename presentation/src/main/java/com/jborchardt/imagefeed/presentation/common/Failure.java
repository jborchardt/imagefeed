package com.jborchardt.imagefeed.presentation.common;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

public class Failure {
    @StringRes
    private int mMessageId;
    private Resolution mResolution;
    
    public Failure(@StringRes int messageId) {
        this(messageId, null);
    }
    
    public Failure(@StringRes int messageId, @Nullable Resolution resolution) {
        mMessageId = messageId;
        mResolution = resolution;
    }
    
    public int getMessageId() {
        return mMessageId;
    }

    public boolean hasResolution() {
        return mResolution != null;
    }

    public void resolve() {
        if (mResolution != null) {
            mResolution.resolve();
        }
    }
}
package com.jborchardt.imagefeed.presentation.common;

/**
 * A representation of a UI module, typically a view.
 */
public interface View {
    void onStart();
    
    void onStop();

    int getId();
}

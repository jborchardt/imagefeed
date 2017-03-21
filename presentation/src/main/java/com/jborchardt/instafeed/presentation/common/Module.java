package com.jborchardt.instafeed.presentation.common;

/**
 * A representation of a UI module, typically a view.
 */
public interface Module {
    void onStart();
    
    void onStop();

    int getId();
}

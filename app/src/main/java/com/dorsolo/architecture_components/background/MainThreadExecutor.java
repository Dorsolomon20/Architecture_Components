package com.dorsolo.architecture_components.background;

import android.os.Handler;

import java.util.concurrent.Executor;

import androidx.annotation.NonNull;

/**
 * Executor on the MainThread
 */
public class MainThreadExecutor implements Executor {

    private Handler mainThreadHandler;

    MainThreadExecutor() {
        mainThreadHandler = new Handler();
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mainThreadHandler.post(command);
    }
}

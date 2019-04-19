package com.dorsolo.architecture_components.background.runnables;

import com.dorsolo.architecture_components.background.AppExecutor;

import androidx.annotation.NonNull;

public class RunnableExecutor {

    /**
     * Invoked in order to execute the provided runnable with the DiskIO Executor
     *
     * @param runnable Runnable obj instance which is to be executed
     */
    public static void execute(@NonNull Runnable runnable) {
        AppExecutor.getAppExecutor().getDiskIO().execute(runnable);
    }
}

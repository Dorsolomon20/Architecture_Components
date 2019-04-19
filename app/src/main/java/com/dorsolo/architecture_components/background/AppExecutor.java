package com.dorsolo.architecture_components.background;

import com.dorsolo.architecture_components.builders.RuntimeExceptionBuilder;
import com.dorsolo.architecture_components.utilites.Precondition;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import static com.dorsolo.architecture_components.utilites.Constants.Background.THREAD_POOL_DEFAULT_SIZE;
import static com.dorsolo.architecture_components.utilites.Constants.Background.THREAD_POOL_MAX;
import static com.dorsolo.architecture_components.utilites.Constants.Background.THREAD_POOL_MIN;

/**
 * This class handles as a utility class for handling different kind of jobs on background threads and on
 * the UI thread/Main thread. It gives a single point of entry to multiple behaviors
 */
public class AppExecutor {

    private static AppExecutor appExecutor;
    private Executor diskIO, networkIO, mainThreadExecutor;

    private AppExecutor(@NonNull Executor diskIO, @NonNull Executor networkIO, @NonNull Executor mainThreadExecutor) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThreadExecutor = mainThreadExecutor;
    }

    /**
     * Acquire an instance of AppExecutor
     *
     * @param nThreads integer value which represent the number of threads that will be in the threadPool of the networkIO executor
     * @return A AppExecutor instance with all executor ready for use
     */
    public static AppExecutor getAppExecutor(@IntRange(from = THREAD_POOL_MIN, to = THREAD_POOL_MAX) int... nThreads) {
        Precondition.checkCondition(RuntimeExceptionBuilder.buildException(RuntimeException.class, "Number of thread must be grated then or equal to 1"), nThreads.length <= 1);
        if (appExecutor == null)
            appExecutor = new AppExecutor(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(nThreads.length == 0 ? THREAD_POOL_DEFAULT_SIZE : nThreads[0]), new MainThreadExecutor());
        return appExecutor;
    }

    public Executor getDiskIO() {
        return diskIO;
    }

    public Executor getNetworkIO() {
        return networkIO;
    }

    public Executor getMainThreadExecutor() {
        return mainThreadExecutor;
    }

    public ExecutorService getExecutorService() {
        return (ExecutorService) diskIO;
    }
}
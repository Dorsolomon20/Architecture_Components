package com.dorsolo.architecture_components.data.repo;

import android.app.Application;

import com.dorsolo.architecture_components.background.runnables.NoteRunnable;
import com.dorsolo.architecture_components.background.runnables.RunnableExecutor;
import com.dorsolo.architecture_components.data.db.AppDatabase;
import com.dorsolo.architecture_components.data.db.daos.NoteDao;
import com.dorsolo.architecture_components.data.db.entites.Note;
import com.dorsolo.architecture_components.utilites.Annotations.Background.ActionType;
import com.dorsolo.architecture_components.utilites.Constants;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class NoteRepository {

    private NoteDao noteDao;

    public NoteRepository(@NonNull Application application) {
        noteDao = AppDatabase.getInstance(application).noteDao();
    }

    /**
     * Invoked in order to execute either a DELETE, UPDATE or INSERT action
     *
     * @param action Integer value which represent the action to be taken
     * @param note   Note obj instance to be inserted, updated or deleted
     */
    public void execute(@ActionType int action, @NonNull Note note) {
        RunnableExecutor.execute(new NoteRunnable(action, note, noteDao));
    }

    /**
     * Invoked in order to clear all of the Notes records from the table
     */
    public void deleteAll() {
        RunnableExecutor.execute(new NoteRunnable(noteDao));
    }

    public LiveData<PagedList<Note>> getAllNotes() {
        return new LivePagedListBuilder<>(noteDao.getAllNotes(), new PagedList.Config.Builder()
                .setEnablePlaceholders(Constants.NotePaging.ENABLE_PLACEHOLDERS)
                .setInitialLoadSizeHint(Constants.NotePaging.INITIAL_LOAD_SIZE)
                .setPageSize(Constants.NotePaging.PAGE_SIZE).build()).build();
    }
}

package com.dorsolo.architecture_components.data.viewModels;

import android.app.Application;

import com.dorsolo.architecture_components.data.db.entites.Note;
import com.dorsolo.architecture_components.data.repo.NoteRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;
    private LiveData<PagedList<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    public NoteRepository getDataAccess() {
        return noteRepository;
    }

    public LiveData<PagedList<Note>> getAllNotes() {
        return allNotes;
    }
}

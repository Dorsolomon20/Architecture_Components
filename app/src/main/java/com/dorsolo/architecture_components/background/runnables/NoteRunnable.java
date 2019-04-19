package com.dorsolo.architecture_components.background.runnables;

import android.annotation.SuppressLint;

import com.dorsolo.architecture_components.data.db.daos.NoteDao;
import com.dorsolo.architecture_components.data.db.entites.Note;
import com.dorsolo.architecture_components.utilites.Annotations.Background.ActionType;

import androidx.annotation.NonNull;

import static com.dorsolo.architecture_components.utilites.Constants.Background.DELETE;
import static com.dorsolo.architecture_components.utilites.Constants.Background.DELETE_ALL;
import static com.dorsolo.architecture_components.utilites.Constants.Background.INSERT;
import static com.dorsolo.architecture_components.utilites.Constants.Background.UPDATE;

public final class NoteRunnable implements Runnable {

    private @ActionType
    int type;
    private Note note;
    private NoteDao noteDao;

    public NoteRunnable(@NonNull NoteDao noteDao) {
        this.type = DELETE_ALL;
        this.noteDao = noteDao;
    }

    public NoteRunnable(@ActionType int type, @NonNull Note note, @NonNull NoteDao noteDao) {
        this.type = type;
        this.note = note;
        this.noteDao = noteDao;
    }

    @SuppressLint("SwitchIntDef")
    @Override
    public void run() {
        switch (type) {
            case INSERT:
                noteDao.insertRecord(note);
                break;
            case DELETE:
                noteDao.deleteRecord(note);
                break;
            case UPDATE:
                noteDao.updateRecord(note);
                break;
            case DELETE_ALL:
                noteDao.deleteAll();
                break;
        }
    }
}

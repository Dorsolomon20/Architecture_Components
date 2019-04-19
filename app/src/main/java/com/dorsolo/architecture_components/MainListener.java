package com.dorsolo.architecture_components;

import com.dorsolo.architecture_components.base.BaseListener;
import com.dorsolo.architecture_components.data.db.entites.Note;

import androidx.annotation.NonNull;

public interface MainListener extends BaseListener {

    /**
     * Invoked once the user has added/edited a Note
     *
     * @param isEdit boolean value which indicate whether the note was created or edited
     * @param note   Note obj instance which contains all of the necessary information
     */
    void notesChange(boolean isEdit, @NonNull Note note);

    /**
     * Invoked once the user has clicked one of the recycler notes in order to open the dialog for
     * editing it
     *
     * @param note Note obj instance which the user has clicked to edit
     */
    void editNote(Note note);
}

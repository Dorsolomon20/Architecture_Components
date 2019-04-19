package com.dorsolo.architecture_components;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dorsolo.architecture_components.base.BaseActivity;
import com.dorsolo.architecture_components.data.db.entites.Note;
import com.dorsolo.architecture_components.dialogs.AddNoteDialog;
import com.dorsolo.architecture_components.fragments.MainFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;

public class MainActivity extends BaseActivity implements MainListener {

    private FloatingActionButton fabAddNote;
    private MainFragment mainFragment;

    /**
     * Listen for clicks on the fabAddNote view in order to open the dialog for adding more notes
     */
    private View.OnClickListener fabAddNoteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getAppUtils().launchDialogFragment(AddNoteDialog.newInstance(), null);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initViews();
        initListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_delete_all:
                mainFragment.deleteAllNotes();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Invoked from the onCreate() method in order to initialize required variables and launch the necessary fragment
     */
    private void init() {
        getAppUtils().launchFragment(mainFragment = MainFragment.newInstance(), false, null);
    }

    /**
     * Invoked from the onCreate() method in order to initialize all of the required views
     */
    private void initViews() {
        fabAddNote = findViewById(R.id.fabAddNote);
    }

    /**
     * Invoked in order to attach user interactions listeners to the relevant views
     */
    private void initListeners() {
        fabAddNote.setOnClickListener(fabAddNoteClickListener);
    }

    /**
     * Invoked once the user has added/edited a Note
     *
     * @param isEdit boolean value which indicate whether the note was created or edited
     * @param note   Note obj instance which contains all of the necessary information
     */
    @Override
    public void notesChange(boolean isEdit, @NonNull Note note) {
        if (isEdit)
            mainFragment.noteEdited(note);
        else
            mainFragment.noteAdded(note);
    }

    /**
     * Invoked once the user has clicked one of the recycler notes in order to open the dialog for
     * editing it
     *
     * @param note Note obj instance which the user has clicked to edit
     */
    @Override
    public void editNote(@NonNull Note note) {
        getAppUtils().launchDialogFragment(AddNoteDialog.newInstance(note), null);
    }
}
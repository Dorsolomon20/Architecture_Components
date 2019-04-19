package com.dorsolo.architecture_components.dialogs;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dorsolo.architecture_components.R;
import com.dorsolo.architecture_components.base.BaseDialogFragment;
import com.dorsolo.architecture_components.data.db.entites.Note;
import com.dorsolo.architecture_components.utilites.FileUtils;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AddNoteDialog extends BaseDialogFragment {

    @Nullable
    private Note editNote;

    private TextInputLayout etTitleContainer, etDescriptionContainer, etPriorityContainer;
    private EditText etTitle, etDescription, etPriority;
    private Button btnSaveNote;

    /**
     * Listen for clicks on the btnSaveNote button in order to save the passed information
     */
    private View.OnClickListener btnSaveNoteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clearErrors();
            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            String priority = etPriority.getText().toString().trim();
            boolean valid = true;
            if (priority.isEmpty()) {
                setErrorMsg(etPriorityContainer);
                etPriority.requestFocus();
                valid = false;
            }
            if (description.isEmpty()) {
                setErrorMsg(etDescriptionContainer);
                etDescription.requestFocus();
                valid = false;
            }
            if (title.isEmpty()) {
                setErrorMsg(etTitleContainer);
                etTitle.requestFocus();
                valid = false;
            }
            if (valid) {
                dismiss();
                Bitmap picture = FileUtils.drawableToBitmap(requireContext(), R.drawable.ic_launcher_background);
                if (picture != null) {
                    Note note = new Note(Integer.valueOf(priority), title, description, FileUtils.bitmapToBase64(
                            picture, 100));
                    if (editNote != null)
                        note.setId(editNote.getId());
                    getMainListener().notesChange(editNote != null, note);
                }
            }
        }
    };

    /**
     * Acquire a new instance of AddNoteDialog
     *
     * @return Newly created AddNoteDialog obj instance
     */
    public static AddNoteDialog newInstance() {
        return new AddNoteDialog();
    }

    /**
     * Acquire a new instance of AddNoteDialog in order edit the provided note
     *
     * @param editNote Note obj instance which the user has chosen to edit
     * @return Newly created AddNoteDialog obj instance
     */
    public static AddNoteDialog newInstance(@NonNull Note editNote) {
        AddNoteDialog fragment = new AddNoteDialog();
        fragment.editNote = editNote;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initListeners();
        populateFields();
    }

    /**
     * Invoked from the onViewCreated() method in order to initialize required views
     *
     * @param view View instance obj which represent the fragment layout viewGroup view
     * @see androidx.fragment.app.Fragment#onViewCreated(View, Bundle)
     */
    private void initViews(View view) {
        etTitleContainer = view.findViewById(R.id.etTitleContainer);
        etTitle = view.findViewById(R.id.etTitle);
        etDescriptionContainer = view.findViewById(R.id.etDescriptionContainer);
        etDescription = view.findViewById(R.id.etDescription);
        etPriorityContainer = view.findViewById(R.id.etPriorityContainer);
        etPriority = view.findViewById(R.id.etPriority);
        btnSaveNote = view.findViewById(R.id.btnSaveNote);
    }

    /**
     * Invoked from the onViewCreate() method in order to attach listeners to initialized views for user
     * interaction
     *
     * @see androidx.fragment.app.Fragment#onViewCreated(View, Bundle)
     */
    private void initListeners() {
        btnSaveNote.setOnClickListener(btnSaveNoteClickListener);
    }

    /**
     * Invoked in order to populate the fields if the dialog is for editing a note
     */
    private void populateFields() {
        if (editNote != null) {
            etTitle.setText(editNote.getTitle());
            etDescription.setText(editNote.getDescription());
            etPriority.setText(String.valueOf(editNote.getPriority()));
        }
    }

    // FIXME: 14/04/2019 Both are Bad methods (setErrorMsg, clearErrors), badly used, just for the sake of this tutorial

    /**
     * Invoked in order to set a default error msg for the TextInputLayout container
     *
     * @param etContainer TextInputLayout obj instance
     */
    private void setErrorMsg(TextInputLayout etContainer) {
        etContainer.setError("אנא מלא את השדה");
    }

    /**
     * Invoked in order to clear all of the errors from the TextInputLayout's
     */
    private void clearErrors() {
        etTitleContainer.setErrorEnabled(false);
        etDescriptionContainer.setErrorEnabled(false);
        etPriorityContainer.setErrorEnabled(false);
    }

    @Override
    public boolean backPressed() {
        return true;
    }
}

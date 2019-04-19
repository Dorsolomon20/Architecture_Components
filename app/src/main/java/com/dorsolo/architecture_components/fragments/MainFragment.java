package com.dorsolo.architecture_components.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dorsolo.architecture_components.R;
import com.dorsolo.architecture_components.adapters.NotesRecyclerAdapter;
import com.dorsolo.architecture_components.base.BaseFragment;
import com.dorsolo.architecture_components.data.db.entites.Note;
import com.dorsolo.architecture_components.data.viewModels.NoteViewModel;
import com.dorsolo.architecture_components.interaction.NoteSwipeHelper;
import com.dorsolo.architecture_components.listeners.SwipeAnimationListener;
import com.dorsolo.architecture_components.utilites.Constants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends BaseFragment implements SwipeAnimationListener {

    private NotesRecyclerAdapter notesRecyclerAdapter;
    private NoteViewModel noteViewModel;

    /**
     * Invoked every time the observed data has changes
     */
    private Observer<PagedList<Note>> notesObserver = new Observer<PagedList<Note>>() {
        @Override
        public void onChanged(PagedList<Note> notes) {
            notesRecyclerAdapter.submitList(notes);
        }
    };

    /**
     * Invoked in order to acquire a new instance of MainFragment
     *
     * @return Newly created instance of MainFragment
     */
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, notesObserver);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    /**
     * Invoked from the onViewCreated() method in order to initialize required views
     *
     * @param view View instance obj which represent the fragment layout viewGroup view
     * @see androidx.fragment.app.Fragment#onViewCreated(View, Bundle)
     */
    private void initViews(View view) {
        RecyclerView notesRecycler = view.findViewById(R.id.notesRecycler);
        notesRecycler.setHasFixedSize(true);
        notesRecycler.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        notesRecycler.setAdapter(notesRecyclerAdapter = new NotesRecyclerAdapter(requireActivity(), getMainListener()));
        new ItemTouchHelper(new NoteSwipeHelper(this)).attachToRecyclerView(notesRecycler);
    }

    /**
     * Invoked once the user has created a new Note in order to write it to the database
     *
     * @param note Note obj instance containing all of the necessary information
     */
    public void noteAdded(@NonNull Note note) {
        noteViewModel.getDataAccess().execute(Constants.Background.INSERT, note);
    }

    /**
     * Invoked once the user has edited a note in order to write it to the database
     *
     * @param note Note obj instance containing all of the necessary information
     */
    public void noteEdited(@NonNull Note note) {
        noteViewModel.getDataAccess().execute(Constants.Background.UPDATE, note);
    }

    /**
     * Invoked once the user choice to delete all of the notes
     */
    public void deleteAllNotes() {
        noteViewModel.getDataAccess().deleteAll();
    }

    /**
     * @param fromPos the starting position of the item
     * @param toPos   the end position of the item
     */
    @Override
    public void onMove(int fromPos, int toPos) {
        //Drag & Drop disabled
    }

    /**
     * @param direction the direction of the swipe
     * @param pos       the position of the swiped item
     */
    @Override
    public void onSwiped(int direction, int pos) {
        noteViewModel.getDataAccess().execute(Constants.Background.DELETE, notesRecyclerAdapter.getNote(pos));
    }
}

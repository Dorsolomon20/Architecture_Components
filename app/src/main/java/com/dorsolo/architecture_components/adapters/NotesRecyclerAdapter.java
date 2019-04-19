package com.dorsolo.architecture_components.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dorsolo.architecture_components.MainListener;
import com.dorsolo.architecture_components.R;
import com.dorsolo.architecture_components.base.BaseRecyclerAdapter;
import com.dorsolo.architecture_components.callbacks.NoteDiffCallback;
import com.dorsolo.architecture_components.data.db.entites.Note;
import com.dorsolo.architecture_components.utilites.FileUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class NotesRecyclerAdapter extends BaseRecyclerAdapter<Note, NotesRecyclerAdapter.ViewHolder> {

    public NotesRecyclerAdapter(@NonNull FragmentActivity activity, @NonNull MainListener mainListener) {
        super(new NoteDiffCallback(), activity, mainListener);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = getItem(position);
        if (note != null) {
            holder.txtTitle.setText(note.getTitle());
            holder.txtDescription.setText(note.getDescription());
            holder.txtPriority.setText(String.valueOf(note.getPriority()));
            holder.imgBackground.setImageBitmap(FileUtils.base64ToBitmap(note.getImg()));
        }
    }

    /**
     * Invoked in order to get a Note based on a provided position
     *
     * @param pos Integer value which represent the position of the Note in the array
     * @return Note obj instance
     */
    public Note getNote(int pos) {
        return getItem(pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private View.OnClickListener onItemViewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION)
                    getMainListener().editNote(getItem(pos));
            }
        };

        private TextView txtTitle, txtDescription, txtPriority;
        private ImageView imgBackground;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
            initListeners(itemView);
        }

        /**
         * Invoked in order to initialize the
         *
         * @param view View obj instance which represent the ViewGroup of the layout of the items
         */
        private void initViews(View view) {
            txtTitle = view.findViewById(R.id.txtTitle);
            txtDescription = view.findViewById(R.id.txtDescription);
            txtPriority = view.findViewById(R.id.txtPriority);
            imgBackground = view.findViewById(R.id.imgBackground);
        }

        /**
         * Invoked from the ViewHolder constructor in order to attach listeners to initialized views
         * for user interaction
         */
        private void initListeners(View view) {
            view.setOnClickListener(onItemViewClickListener);
        }
    }
}

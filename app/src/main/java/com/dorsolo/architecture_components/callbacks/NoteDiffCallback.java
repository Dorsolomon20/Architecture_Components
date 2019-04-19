package com.dorsolo.architecture_components.callbacks;

import com.dorsolo.architecture_components.data.db.entites.Note;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

/**
 * Two method used to set the logic for comparing between two Notes, in order to set the correct notify method
 */
public class NoteDiffCallback extends DiffUtil.ItemCallback<Note> {

    @Override
    public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
        return oldItem.equals(newItem);
    }
}

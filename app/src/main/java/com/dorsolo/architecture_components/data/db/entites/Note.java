package com.dorsolo.architecture_components.data.db.entites;

import com.dorsolo.architecture_components.utilites.Constants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Constants.Database.NOTE_TABLE_NAME)
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int priority;
    private String title, description;
    private String img;

    public Note(int priority, @NonNull String title, @NonNull String description, @NonNull String img) {
        this.priority = priority;
        this.title = title;
        this.description = description;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getImg() {
        return img;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Note) {
            Note note = (Note) obj;
            return note.getTitle().equals(title) &&
                    note.getDescription().equals(description) &&
                    note.getPriority() == priority;
        }
        return false;
    }
}

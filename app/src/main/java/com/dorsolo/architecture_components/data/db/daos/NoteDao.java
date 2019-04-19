package com.dorsolo.architecture_components.data.db.daos;

import com.dorsolo.architecture_components.data.db.Queries;
import com.dorsolo.architecture_components.data.db.base.BaseDao;
import com.dorsolo.architecture_components.data.db.entites.Note;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface NoteDao extends BaseDao<Note> {

    @Query(Queries.NOTE.DELETE_ALL)
    void deleteAll();

    @Query(Queries.NOTE.GET_ALL_BY_PRIORITY)
    DataSource.Factory<Integer, Note> getAllNotes();
}

package com.dorsolo.architecture_components.data.db.base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

@Dao
public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecord(T record);

    @Delete
    void deleteRecord(T t);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRecord(T t);
}

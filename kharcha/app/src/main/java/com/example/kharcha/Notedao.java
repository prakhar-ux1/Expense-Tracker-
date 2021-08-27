package com.example.kharcha;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.kharcha.Note;

import java.util.List;

@Dao
public interface Notedao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete_one(Note note);

    @Query("DELETE FROM note_table")
    void Delete_all ();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT sum(Amount) FROM note_table where exp=0")
    LiveData< Float> getSum();
}

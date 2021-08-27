package com.example.kharcha;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewmodel extends AndroidViewModel {
    private  NoteRepository noteRepository_1;
    private LiveData<List<Note>> noteslist;
    private LiveData<Float> Total;
    public NoteViewmodel( Application application) {

        super(application);
        noteRepository_1 =new NoteRepository(application);
        noteslist=noteRepository_1.getAllnotes();
        Total=noteRepository_1.getTotal();
    }
    public void insert(Note note)
    {
        noteRepository_1.insert(note);
    }
    public void update(Note note)
    {
        noteRepository_1.Repoupdate(note);
    }
    public void delete_one(Note note)
    {
        noteRepository_1.repodeleteone(note);
    }
    public void delete()
    {
        noteRepository_1.repodelete();
    }

    public LiveData<List<Note>> getNoteslist() {
        return noteslist;
    }
    public  LiveData<Float> getTotal(){
        return  Total;
    }
}

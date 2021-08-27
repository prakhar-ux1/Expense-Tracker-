package com.example.kharcha;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private Notedao notedao_1;
    private LiveData<List<Note>> allnotes;
    private LiveData<Float> total;

    public NoteRepository(Application application)
    {
        NoteDatabase database=NoteDatabase.getInstance(application);
        notedao_1=database.notedao();
        allnotes=notedao_1.getAllNotes();
        total=notedao_1.getSum();

    }
     public void insert(Note note){
        new insertAysnc(notedao_1).execute(note);

     }
     public void Repoupdate(Note note)
     {
         new updateAysnc(notedao_1).execute(note);
     }
     public void repodeleteone(Note note)
     {
         new deleteAysnc(notedao_1).execute(note);
     }
     public void repodelete()
     {
         new deleteallAysnc(notedao_1).execute();
     }

    public LiveData<List<Note>> getAllnotes() {
        return allnotes;
    }
    public LiveData<Float> getTotal()
    {
        return  total;
    }

    private static class  insertAysnc extends AsyncTask<Note,Void,Void>
     {
         private Notedao n1;

         private insertAysnc(Notedao n1)
         {
             this.n1 = n1;
         }
         @Override
         protected Void doInBackground(Note... notes) {
             n1.insert(notes[0]);
             return null;
         }
     }
     private static class updateAysnc extends AsyncTask<Note,Void,Void>
    {
        public updateAysnc(Notedao n1) {
            this.n1 = n1;
        }

        private  Notedao n1;


        @Override
        protected Void doInBackground(Note... notes) {

            n1.update(notes[0]);
            return null;
        }
    }

    private  static  class deleteAysnc extends AsyncTask<Note,Void,Void>
    {
        private Notedao N2;

        public deleteAysnc(Notedao n2) {
            N2 = n2;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            N2.delete_one(notes[0]);
            return null;
        }
    }

    private static class deleteallAysnc extends AsyncTask<Void,Void,Void>
    {
        private Notedao n3;

        public deleteallAysnc(Notedao n3) {
            this.n3 = n3;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            n3.Delete_all();
            return null;
        }
    }
}

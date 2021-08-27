package com.example.kharcha;

import android.content.Context;
import android.os.AsyncTask;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
  public static NoteDatabase instance;

  public abstract Notedao notedao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration().addCallback(callback)
                    .build();
        }
        return instance;

    }
    private static  RoomDatabase.Callback callback=new RoomDatabase.Callback()
    {

        @Override
        public void onCreate( SupportSQLiteDatabase db) {
            super.onCreate(db);
            new popolateAsync(instance).execute();
        }
    };


    private static  class  popolateAsync extends AsyncTask<Void,Void,Void>
    {
      private  Notedao n1;
      public popolateAsync(NoteDatabase database)
      {
          n1=database.notedao();
      }
        @Override
        protected Void doInBackground(Void... voids) {
            n1.insert(new Note("25/01/2016","book",6542,15,1));
            n1.insert(new Note("25/01/2017","salary",6542,41,1));
            n1.insert(new Note("25/01/2018","entertainment",6542,51,0));
            n1.insert(new Note("25/01/2003","Fuel",6542,45,1));
            return null;
        }
    }



}

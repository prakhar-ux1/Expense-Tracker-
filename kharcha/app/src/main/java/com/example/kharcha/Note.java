package com.example.kharcha;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;

    private String category;

    private float Amount;

    private int priority;
   // private Date date_input;
    private  int exp;

    public Note(String date, String category, float Amount, int priority,int exp ) {
        this.date = date;
        this.category = category;
        this.Amount = Amount;
        this.priority = priority;
        this.exp=exp;
    }
   /* public Note(Date date,String category,int exp,float Amount )
    {
        this.date_input=date;
        this.category=category;
        this.exp=exp;
        this.Amount=Amount;
    }*/

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public float getAmount() {
        return Amount;
    }

    public int getPriority() {
        return priority;
    }

    public int getId() {
        return id;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }


}

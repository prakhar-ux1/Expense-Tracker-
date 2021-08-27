package com.example.kharcha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
public static final int requestcode_add =2;
     private NoteViewmodel noteviewmodel;

    RecyclerView R1;
   MyrecyclerAdapter myrecyclerAdapter;
   FloatingActionButton floatingActionButton;
   TextView total_view;

   String S1, S2;
   ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton=findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intentforadd =new Intent(MainActivity.this,Addactivity.class);
                 startActivityForResult(intentforadd,requestcode_add);
             //   enter_amount();
            }
        });

        R1 =(RecyclerView)findViewById(R.id.recycler_1);
        R1.setHasFixedSize(true);
        R1.setLayoutManager(new LinearLayoutManager(this));

        myrecyclerAdapter =new MyrecyclerAdapter();
        R1.setAdapter(myrecyclerAdapter);
        total_view=findViewById(R.id.Total_Expense_view);
        noteviewmodel = new ViewModelProvider(this).get(NoteViewmodel.class);
        noteviewmodel.getNoteslist().observe(this, new Observer<List<Note>>() {
             @Override
             public void onChanged(List<Note> notes) {

                 myrecyclerAdapter.setNotes(notes);

             }
         });
         LiveData<Float> sa=noteviewmodel.getTotal();
         Float sas =sa.getValue();
        noteviewmodel.getTotal().observe(this, new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                if(aFloat!=null)
                total_view.setText("Total Expense - "+aFloat.toString());
            }
        });
    }

    public void enter_amount()
    {
        AlertDialog.Builder A1= new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.amountentering,null);
        final EditText e1=view.findViewById(R.id.paise);

        Button cancel =view.findViewById(R.id.cancel);
        Button save= view.findViewById(R.id.save);
         A1.setView(view);

         final AlertDialog alertDialog=A1.create();
         alertDialog.setCanceledOnTouchOutside(false);

         cancel.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 alertDialog.dismiss();
             }
         });



         save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 S1 =e1.getText().toString().trim();
                 Toast.makeText(getApplicationContext(),"hdjf",Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();

             }
         });
         alertDialog.show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==requestcode_add&&resultCode==2)
        {
            float AMOUNT =0;
            try {
            AMOUNT = Float.parseFloat((data.getStringExtra("amount_1")));
              } catch (Exception e) {
            Log.i( "onActivityResult: float", String.valueOf(e));
              }
               ;
            if(AMOUNT==0)
                return;

           String CATEGORY =data.getStringExtra("category");
           String DATE =data.getStringExtra("Date");
           int PRIOR =Integer.parseInt(data.getStringExtra("prior"));
           int expert=Integer.parseInt(data.getStringExtra("exp"));
           Note notes =new Note(DATE,CATEGORY,AMOUNT,PRIOR,expert);

           noteviewmodel.insert(notes);
           // ..insert(note);
            Toast.makeText(getApplicationContext(),AMOUNT+CATEGORY+DATE+" & "+data.getStringExtra("exp")+expert,Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }

    }
}


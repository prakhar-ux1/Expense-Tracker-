package com.example.kharcha;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Addactivity extends AppCompatActivity {
    SharedPreferences mpred;
    EditText e1;
    String t23="0";
    TextView e2, t1;
    String amount,category,date,prior;
    Switch aSwitch;
    public static final int RESULT =2;
    public static  String priority_1 = "";
    ArrayList<category_def> list = new ArrayList<>();


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addactivity);
        e1=(EditText)findViewById(R.id.Amount);
        aSwitch=findViewById(R.id.switch1);
        list.add(new category_def(R.drawable.ic_remove_circle_outline_black_24dp,"BOOk",-1));
        list.add(new category_def(R.drawable.ic_remove_circle_outline_black_24dp,"FUEL",-1));
        list.add(new category_def(R.drawable.ic_remove_circle_outline_black_24dp,"TRAVEL",-1));
        list.add(new category_def(R.drawable.ic_remove_circle_outline_black_24dp,"SUGAR",-1));
        e2=findViewById(R.id.selectcategory);
          t1=findViewById(R.id.date);
          t1.setText("Today");
          e2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  select_category();
              }
          });
          t1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  handledate();
              }
          });
          amount=e1.getText().toString().trim();
          category=e2.getText().toString().trim();
          Calendar c2 =Calendar.getInstance();
          date = DateFormat.format("dd MMMM yy",c2).toString();
          prior=DateFormat.format("yyyyMMdd",c2).toString().trim();

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");
        aSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    t23="1";
                    buttonView.setText("Income");
                }
                else
                {
                    t23="0";
                    buttonView.setText("Expenses");
                }

            }
        });






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

     MenuInflater menuInflater=getMenuInflater();
     menuInflater.inflate(R.menu.add_menu,menu);
     return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                save_note();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public  void handledate()
    {
        Calendar c= Calendar.getInstance();
        int year_1= c.get(Calendar.YEAR);
        int month_1 =c.get(Calendar.MONTH);
        int day_1 =c.get(Calendar.DATE);
        DatePickerDialog D1= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar c1 = Calendar.getInstance();
                c1.set(Calendar.YEAR,year);
                c1.set(Calendar.MONTH,month);
                c1.set(Calendar.DATE,dayOfMonth);
                date = DateFormat.format("dd MMMM yyyy",c1).toString().trim();
                prior=DateFormat.format("yyyyMMdd",c1).toString().trim();
                priority_1=DateFormat.format("dd/MM/yyyy",c1).toString().trim();

                t1.setText(priority_1);




            }
        },year_1,month_1,day_1);
        D1.show();
    }
    public void save_note()
    {
        amount=e1.getText().toString().trim();
        category=e2.getText().toString().trim();
        if(amount.isEmpty()||category.isEmpty()||date.isEmpty()||prior.isEmpty())
            Toast.makeText(getApplicationContext(),"dfdgd,",Toast.LENGTH_SHORT).show();
        Intent intent =new Intent();
        //Note temp=new Note(date,category,Float.parseFloat(amount),Integer.parseInt(prior));
       // intent.putExtra("esdsd",temp);
        intent.putExtra("amount_1",amount);
        intent.putExtra("category",category);
        intent.putExtra("Date",date);
        intent.putExtra("prior",prior);

        intent .putExtra("exp",t23);
        setResult(RESULT,intent);
        finish();
    }
    public void select_category()
    {

        AlertDialog.Builder A2=new AlertDialog.Builder(this);
        View in  =getLayoutInflater().inflate(R.layout.listview_category,null);
        ListView l1=in.findViewById(R.id.listview_1);
        A2.setView(in);
        CategoryAdp adapter =new CategoryAdp(this,list);
        l1.setAdapter(adapter);

        final AlertDialog Alert2 =A2.create();
        Alert2.setCanceledOnTouchOutside(false);
        Alert2.show();
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category_def  user= (category_def)parent.getItemAtPosition(position);
                e2.setText(user.getName());
                Alert2.dismiss();
            }
        });


    }

}

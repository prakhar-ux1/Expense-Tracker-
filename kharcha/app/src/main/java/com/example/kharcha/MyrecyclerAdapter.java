package com.example.kharcha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MyrecyclerAdapter extends RecyclerView.Adapter<MyrecyclerAdapter.Myviewholder> {

    private List<Note> notes= new ArrayList<>();
    private  Context context;

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }





    @Override
    public Myviewholder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater in =LayoutInflater.from(parent.getContext());
        View view=in.inflate(R.layout.row_layout,parent,false);
        context=parent.getContext();
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder( Myviewholder holder, int position) {

        Note n1= notes.get(position);
        holder.t1.setText(n1.getDate());
        holder.t2.setText(String.valueOf(n1.getAmount()));
        holder.t3.setText(n1.getCategory());
        if(n1.getExp()==1)
        {
            holder.t2.setTextColor(ContextCompat.getColor(context,R.color.amount_green));
        }
        else
            holder.t2.setTextColor(ContextCompat.getColor(context,R.color.amount_red));

       // holder.t4.setText(String.valueOf(n1.getPriority()));


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        public TextView t1,t2,t3;
        public Myviewholder( View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.textView);
            t2=itemView.findViewById(R.id.amount);
            t3=itemView.findViewById(R.id.cato);
         //   t4=itemView.findViewById(R.id.priority);
        }
    }
}

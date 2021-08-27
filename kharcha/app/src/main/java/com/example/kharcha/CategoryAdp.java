package com.example.kharcha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class CategoryAdp extends ArrayAdapter<category_def>
{
    public CategoryAdp(Context context, ArrayList<category_def> user) {
        super(context,0,user);
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        category_def user =getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_row, parent, false);
        }
        TextView name=convertView.findViewById(R.id.category_row);
        ImageView image= convertView.findViewById(R.id.sideimage);
        image.setImageResource(user.getImg());
        name.setText(user.getName());
        return convertView;


    }
}
